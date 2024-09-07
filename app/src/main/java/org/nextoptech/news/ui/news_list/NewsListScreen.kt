package org.nextoptech.news.ui.news_list

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import org.nextoptech.news.data.data_source.local.NewsEntity
import org.nextoptech.news.utils.NetworkConnectionState


@Composable
fun NewsListScreenRoute(
    onNewsClick: (String) -> Unit
) {

    val newsListViewModel: NewsListViewModel = hiltViewModel()

    NewsListScreen(
        page = newsListViewModel.page.intValue,
        newsList = newsListViewModel.newsListState,
        onNextPage = newsListViewModel::nextPage,
        loading = newsListViewModel.loading.value,
        error = newsListViewModel.errorState.value,
        getDataOffline = newsListViewModel::getNewsListFromLocalDataSource,
        getDataFromRemoteDataSource = newsListViewModel::loadData,
        onChangeArticleScrollPosition = newsListViewModel::onChangeScrollPosition
    ) { newsString ->
        onNewsClick(newsString)
    }

}


@SuppressLint("SuspiciousIndentation")
@Composable
fun NewsListScreen(
    page: Int,
    newsList: MutableState<List<NewsEntity>>,
    onNextPage: () -> Unit,
    loading: Boolean,
    error: String,
    onChangeArticleScrollPosition: (Int) -> Unit,
    getDataFromRemoteDataSource: () -> Unit,
    getDataOffline: () -> Unit,
    onNewsDetailsClick: (String) -> Unit,
) {


    val context = LocalContext.current
    val connectionState by rememberConnectivityState()

    val isConnected by remember(connectionState) {
        derivedStateOf {
            connectionState === NetworkConnectionState.Available
        }
    }


    if (isConnected) {

        Toast.makeText(context, "You are ONLINE.", Toast.LENGTH_SHORT).show()
        OnlineNewsListComponent(
            page = page,
            newsList = newsList,
            onNextPage = onNextPage,
            loading = loading,
            error = error,
            onChangeArticleScrollPosition = onChangeArticleScrollPosition,
            getDataFromRemoteDataSource = getDataFromRemoteDataSource,
            onNewsDetailsClick = onNewsDetailsClick
        )

    } else {

        Toast.makeText(context, "You are OFFLINE.", Toast.LENGTH_SHORT).show()

        OfflineNewsListComponent(
            loading = loading,
            error = error,
            newsList = newsList,
            getDataOffline = getDataOffline,
            onNewsDetailsClick = onNewsDetailsClick
        )
    }

}
