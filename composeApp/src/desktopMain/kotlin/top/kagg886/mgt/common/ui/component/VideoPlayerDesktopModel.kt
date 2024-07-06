package top.kagg886.mgt.common.ui.component

import top.kagg886.mgt.common.util.BaseAction
import top.kagg886.mgt.common.util.BaseState
import top.kagg886.mgt.common.util.BaseViewModel
import uk.co.caprica.vlcj.factory.MediaPlayerFactory

class VideoPlayerDesktopModel : BaseViewModel<VideoPlayerVMState, VideoPlayerVMAction>(VideoPlayerVMState.Loading) {
    override suspend fun onAction(action: VideoPlayerVMAction) {
        when (action) {
            VideoPlayerVMAction.StartLoading -> {
                setState(VideoPlayerVMState.Loading)
                kotlin.runCatching {
                    MediaPlayerFactory()
                }.onSuccess {
                    setState(VideoPlayerVMState.LoadSuccess)
                }.onFailure {
                    println(it)
                    setState(VideoPlayerVMState.LoadFail)
                }
            }
        }
    }

}

sealed interface VideoPlayerVMState : BaseState {
    data object Loading : VideoPlayerVMState
    data object LoadSuccess : VideoPlayerVMState
    data object LoadFail : VideoPlayerVMState
}

sealed interface VideoPlayerVMAction : BaseAction {
    data object StartLoading : VideoPlayerVMAction
}