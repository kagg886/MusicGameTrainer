package top.kagg886.mgt.common.util

import androidx.compose.runtime.*
import kotlinx.coroutines.delay

@Composable
fun <T> collectStateFromNoComposable(provider: (T) -> MutableState<T> = { mutableStateOf(it) }, block: () -> T): State<T> {
    val state = remember { provider(block()) }
    LaunchedEffect(Unit) {
       while (true) {
           delay(1)
           state.value = block()
       }
    }
    return state
}

