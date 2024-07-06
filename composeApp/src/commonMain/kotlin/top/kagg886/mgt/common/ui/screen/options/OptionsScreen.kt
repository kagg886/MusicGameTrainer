package top.kagg886.mgt.common.ui.screen.options

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import moe.tlaster.precompose.navigation.RouteBuilder
import org.jetbrains.compose.resources.stringResource
import top.kagg886.mgt.common.Res
import top.kagg886.mgt.common.file
import top.kagg886.mgt.common.open_config_file
import top.kagg886.mgt.common.open_config_file_helper
import top.kagg886.mgt.common.ui.component.Actions
import top.kagg886.mgt.common.ui.component.Title
import top.kagg886.mgt.common.util.openConfigFile

@Composable
private fun OptionsScreen() {
    Column {
        Title(stringResource(Res.string.file))
        Actions(stringResource(Res.string.open_config_file),stringResource(Res.string.open_config_file_helper)) {
            openConfigFile()
        }
    }
}

const val OPTIONS_ROUTE = "options"

fun RouteBuilder.configureOptionsScreen() {
    scene(OPTIONS_ROUTE) {
        OptionsScreen()
    }
}