package top.kagg886.mgt.common.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Title(msg: String) {
    Text(
        msg,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(top = 16.dp, bottom = 16.dp, start = 32.dp, end = 32.dp),
    )
}

@Composable
fun Actions(
    msg: String,
    description: String? = null,
    icon: @Composable () -> Unit = {},
    onClick: () -> Unit = {}
) {
    ListItem(headlineContent = {
        Text(msg)
    }, supportingContent = {
        description?.let {
            Text(description)
        }
    }, leadingContent = icon, modifier = Modifier.clickable(onClick = onClick))
}