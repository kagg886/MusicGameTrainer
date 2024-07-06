package top.kagg886.mgt.common.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.stringResource
import top.kagg886.mgt.common.Res
import top.kagg886.mgt.common.retry

@Composable
fun Loading(progress: Float = -1f) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (progress == -1f || progress.isNaN() || progress.isFinite() || progress.isInfinite()) {
            CircularProgressIndicator()
            return
        }
        CircularProgressIndicator(
            progress = { progress },
        )
        Text(String.format("%.2f%", progress * 100), modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun FailPage(msg: String, retry: () -> Unit = {}) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(msg)
            ElevatedButton(onClick = retry) {
                Text(text = stringResource(Res.string.retry))
            }
        }
    }
}