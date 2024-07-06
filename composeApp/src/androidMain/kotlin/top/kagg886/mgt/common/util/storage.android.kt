package top.kagg886.mgt.common.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.suspendCancellableCoroutine
import org.jetbrains.compose.resources.getString
import top.kagg886.mgt.App
import top.kagg886.mgt.common.Res
import top.kagg886.mgt.common.open_config_file_not_supported
import top.kagg886.mgt.common.sendSnackBar
import java.io.File
import kotlin.coroutines.Continuation

actual fun getConfigRootFile(): File = App.getApp().filesDir
actual fun openConfigFile() {
    val ctx = App.getApp()
    val clip = ctx.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
    clip.setPrimaryClip(ClipData.newPlainText("config file", getConfigRootFile().absolutePath))

    runBlocking {
        sendSnackBar(getString(Res.string.open_config_file_not_supported))
    }
}

lateinit var launcher: ActivityResultLauncher<String>
lateinit var continuation: Continuation<Uri?>

actual suspend fun selectFile(): FileInfo? {
    launcher.launch("*/*")
    val uri = suspendCancellableCoroutine {
        continuation = it
    }
    if (uri == null) {
        return null
    }

    return FileInfo(uri.path!!.substringAfterLast("/"),App.getApp().contentResolver.openInputStream(uri)!!)
}