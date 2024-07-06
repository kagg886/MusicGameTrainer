package top.kagg886.mgt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import top.kagg886.mgt.common.App
import top.kagg886.mgt.common.util.continuation
import top.kagg886.mgt.common.util.launcher
import kotlin.coroutines.resume

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launcher = registerForActivityResult(ActivityResultContracts.GetContent()) {
            continuation.resume(it)
        }
        setContent {
            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}