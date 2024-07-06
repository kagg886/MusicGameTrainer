package top.kagg886.mgt

import android.annotation.SuppressLint
import android.app.Application
import androidx.activity.ComponentActivity
import java.lang.reflect.Field
import java.lang.reflect.InvocationTargetException

class App:Application() {
    override fun onCreate() {
        super.onCreate()
    }

    companion object {
        @SuppressLint("DiscouragedPrivateApi", "PrivateApi")
        fun getApp(): App {
            var application: Application? = null
            try {
                val atClass = Class.forName("android.app.ActivityThread")
                val currentApplicationMethod = atClass.getDeclaredMethod("currentApplication")
                currentApplicationMethod.isAccessible = true
                application = currentApplicationMethod.invoke(null) as Application
            } catch (ignored: Exception) {
            }
            if (application != null) return application as App
            try {
                val atClass = Class.forName("android.app.AppGlobals")
                val currentApplicationMethod = atClass.getDeclaredMethod("getInitialApplication")
                currentApplicationMethod.isAccessible = true
                application = currentApplicationMethod.invoke(null) as Application
            } catch (ignored: Exception) {
            }
            return application as App
        }

        fun currentActivity(): ComponentActivity {
            var current: ComponentActivity? = null
            try {
                val activityThreadClass = Class.forName("android.app.ActivityThread")
                val activityThread = activityThreadClass.getMethod("currentActivityThread").invoke(
                    null
                )
                val activitiesField: Field = activityThreadClass.getDeclaredField("mActivities")
                activitiesField.isAccessible = true
                for (activityRecord in (activitiesField.get(activityThread) as Map<*, *>).values) {
                    val activityRecordClass: Class<*> = activityRecord!!.javaClass
                    val pausedField: Field = activityRecordClass.getDeclaredField("paused")
                    pausedField.isAccessible = true
                    if (!pausedField.getBoolean(activityRecord)) {
                        val activityField: Field = activityRecordClass.getDeclaredField("activity")
                        activityField.isAccessible = true
                        current = activityField.get(activityRecord) as ComponentActivity?
                    }
                }
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            } catch (e: InvocationTargetException) {
                e.printStackTrace()
            } catch (e: NoSuchMethodException) {
                e.printStackTrace()
            } catch (e: NoSuchFieldException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }
            return current ?: throw IllegalStateException("Background!")
        }
    }
}

