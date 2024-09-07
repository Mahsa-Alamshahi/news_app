package org.nextoptech.news.ui.news_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import androidx.compose.ui.platform.LocalContext
import org.nextoptech.news.utils.NetworkConnectionState
import org.nextoptech.news.utils.currentConnectivityState
import org.nextoptech.news.utils.observeConnectivityAsFlow


@Composable
fun rememberConnectivityState(): State<NetworkConnectionState> {
    val context = LocalContext.current

    return produceState(initialValue = context.currentConnectivityState) {
        context.observeConnectivityAsFlow().collect {
            value = it
        }
    }
}