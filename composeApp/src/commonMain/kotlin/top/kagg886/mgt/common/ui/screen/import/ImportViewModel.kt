package top.kagg886.mgt.common.ui.screen.import

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import top.kagg886.mgt.common.lib.Practice
import top.kagg886.mgt.common.util.*

class ImportViewModel : BaseViewModel<ImportState, ImportAction>(ImportState.WaitLoading) {

    override suspend fun onAction(action: ImportAction) {
        when (action) {
            ImportAction.SelectFile -> {
                setState(ImportState.Loading())
                val platformFile = selectFile()
                if (platformFile == null) {
                    setState(ImportState.WaitLoading)
                    return
                }
                val fName = platformFile.name
                val s = platformFile.stream

                withContext(Dispatchers.IO) {
                    val all = s.available().toFloat()
                    var x = 0
                    Practice.import(fName).use {
                        s.readBuffered(ByteArray(2048)) { arr, len ->
                            it.write(arr, 0, len)
                            x += len
                            setState(ImportState.Loading(x / all))
                        }
                    }
                }
                setState(ImportState.LoadingSuccess)
            }
        }
    }
}

sealed interface ImportState : BaseState {
    data class Loading(val i: Float = -1f) : ImportState
    data object WaitLoading : ImportState
    data object LoadingSuccess : ImportState
}

sealed interface ImportAction : BaseAction {
    data object SelectFile : ImportAction
}