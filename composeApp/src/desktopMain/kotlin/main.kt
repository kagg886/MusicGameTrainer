import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.jetbrains.compose.resources.stringResource
import top.kagg886.mgt.common.App
import top.kagg886.mgt.common.Res
import top.kagg886.mgt.common.app_name

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = stringResource(Res.string.app_name),
    ) {
        App()
    }
}