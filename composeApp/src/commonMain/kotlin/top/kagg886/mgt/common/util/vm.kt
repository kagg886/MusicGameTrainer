package top.kagg886.mgt.common.util

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

abstract class BaseViewModel<State : BaseState, Action : BaseAction>(initState: State) : ViewModel() {
    private val _state = MutableStateFlow(initState)
    val state = _state.asStateFlow()

    fun dispatch(action: Action) {
        viewModelScope.launch {
            onAction(action)
        }
    }

    fun setState(state: State) {
        _state.value = state
    }

    abstract suspend fun onAction(action: Action)
}

interface BaseAction
interface BaseState