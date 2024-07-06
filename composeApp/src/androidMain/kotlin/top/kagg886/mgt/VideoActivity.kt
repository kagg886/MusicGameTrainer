package top.kagg886.mgt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import top.kagg886.mgt.common.VideoApp
import top.kagg886.mgt.common.lib.Practice
import java.io.File

class VideoActivity:ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val root = File(intent.getStringExtra("file")!!)
        setContent {
            VideoApp(Practice(root))
        }
    }
}