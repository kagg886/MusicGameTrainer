package top.kagg886.mgt.common.ui.screen.import

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import moe.tlaster.precompose.navigation.RouteBuilder
import moe.tlaster.precompose.viewmodel.viewModel
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.stringResource
import top.kagg886.mgt.common.*
import top.kagg886.mgt.common.ui.component.Actions
import top.kagg886.mgt.common.ui.component.Loading
import top.kagg886.mgt.common.ui.component.Title
import top.kagg886.mgt.common.ui.screen.home.HOME_ROUTE

@Composable
private fun ImportScreen() {
    val model = viewModel {
        ImportViewModel()
    }
    val state by model.state.collectAsState()

    when (state) {
        is ImportState.Loading -> {
            Loading((state as ImportState.Loading).i)
        }

        ImportState.WaitLoading -> {
            Column {
                Title(stringResource(Res.string.select_import_type))
                Actions(stringResource(Res.string.select_on_local)) {
                    model.dispatch(ImportAction.SelectFile)
                }
            }
        }

        ImportState.LoadingSuccess -> {
            val nav = LocalNavigation.current
            LaunchedEffect(Unit) {
                sendSnackBar(getString(Res.string.import_success))
                nav.navigate(HOME_ROUTE)
            }
        }
    }

}

const val IMPORT_ROUTE = "import"

fun RouteBuilder.configureImportScreen() {
    scene(IMPORT_ROUTE) {
        ImportScreen()
    }
}
