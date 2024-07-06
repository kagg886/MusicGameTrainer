package top.kagg886.mgt.common.ui.screen.home

import org.jetbrains.compose.resources.getString
import top.kagg886.mgt.common.Res
import top.kagg886.mgt.common.delete_success
import top.kagg886.mgt.common.lib.Practice
import top.kagg886.mgt.common.lib.practiceRoot
import top.kagg886.mgt.common.sendSnackBar
import top.kagg886.mgt.common.util.BaseAction
import top.kagg886.mgt.common.util.BaseState
import top.kagg886.mgt.common.util.BaseViewModel
import java.io.File

expect fun startVideoApp(file: File)

class HomeViewModel : BaseViewModel<HomeState, HomeAction>(HomeState.Loading) {
    private fun getPracticeList(): List<File> = practiceRoot.listFiles()!!.toList()

    override suspend fun onAction(action: HomeAction) {
        when (action) {
            HomeAction.StartFetching -> {
                setState(HomeState.Loading)
                kotlin.runCatching {
                    setState(HomeState.LoadSuccess(getPracticeList().map { Practice(it) }))
                }.onFailure {
                    setState(HomeState.LoadFail(it.message!!))
                }
            }

            is HomeAction.Delete -> {
                setState(HomeState.Loading)
                getPracticeList().first { it == action.practice.root }.deleteRecursively()
                sendSnackBar(getString(Res.string.delete_success))
                dispatch(HomeAction.StartFetching)
            }

            is HomeAction.StartPractice -> {
                setState(HomeState.Loading)
                startVideoApp(action.file)
                dispatch(HomeAction.StartFetching)
            }
        }
    }
}

sealed interface HomeState : BaseState {
    data object Loading : HomeState
    data class LoadSuccess(val list: List<Practice>) : HomeState
    data class LoadFail(val msg: String) : HomeState
}

sealed interface HomeAction : BaseAction {
    data object StartFetching : HomeAction
    data class Delete(val practice: Practice) : HomeAction
    data class StartPractice(val file: File) : HomeAction
}