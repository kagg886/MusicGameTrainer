package top.kagg886.mgt.common.ui.screen.home

import android.content.Intent
import top.kagg886.mgt.App
import top.kagg886.mgt.VideoActivity
import java.io.File

actual fun startVideoApp(file: File) {
    App.currentActivity().apply {
        startActivity(
            Intent(this,VideoActivity::class.java)
                .putExtra("file",file.absolutePath)
        )
    }
}