import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.jetbrains.compose.resources.stringResource
import top.kagg886.mgt.common.Res
import top.kagg886.mgt.common.VideoApp
import top.kagg886.mgt.common.app_name
import top.kagg886.mgt.common.lib.Practice
import java.io.File
import kotlin.concurrent.thread

fun launchVideoWindow(file: File) = thread {
    application(exitProcessOnExit = false) {
        var exit by remember { mutableStateOf(false) }
        if (!exit) {
            Window(
                onCloseRequest = { exit = true },
                title = stringResource(Res.string.app_name),
            ) {
                VideoApp(Practice(file))
            }
        }
    }
}