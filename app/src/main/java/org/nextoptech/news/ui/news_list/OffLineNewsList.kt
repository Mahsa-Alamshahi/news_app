package org.nextoptech.news.ui.news_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import org.nextoptech.news.data.data_source.local.NewsEntity
import org.nextoptech.news.ui.component.EmptyList
import org.nextoptech.news.ui.component.LoadingComponent

@Composable
fun OfflineNewsListComponent(
    newsList: MutableState<List<NewsEntity>>,
    getDataOffline: () -> Unit,
    onNewsDetailsClick: (String) -> Unit,
    loading: Boolean,
    error: String
) {


    LaunchedEffect(key1 = Unit) {
        getDataOffline()
    }


    Scaffold { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {

            if (loading && newsList.value.isEmpty()) {
                LoadingComponent()
            } else if (!loading && newsList.value.isEmpty()) {
                EmptyList("List is empty.")
            } else if (!loading && error != "") {
                EmptyList(error)
            } else {

                LazyColumn {
                    itemsIndexed(items = newsList.value) { index, news ->
                        news.let {
                            NewsListItem(
                                news = it
                            ) { news ->
                                onNewsDetailsClick(news)
                            }
                        }
                    }
                }
            }
        }
    }
}