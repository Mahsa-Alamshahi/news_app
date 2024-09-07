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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.orhanobut.logger.Logger
import org.nextoptech.news.common.AppConstant
import org.nextoptech.news.data.data_source.local.NewsEntity
import org.nextoptech.news.ui.component.EmptyList
import org.nextoptech.news.ui.component.LoadingComponent



@Composable
fun OnlineNewsListComponent(
    page: Int,
    newsList: MutableState<List<NewsEntity>>,
    onNextPage: () -> Unit,
    loading: Boolean,
    onChangeArticleScrollPosition: (Int) -> Unit,
    getDataFromRemoteDataSource: () -> Unit,
    onNewsDetailsClick: (String) -> Unit,
    error: String
) {


    LaunchedEffect(key1 = Unit) {
        getDataFromRemoteDataSource()
    }


    Scaffold {contentPadding ->

   Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
    ) {

        if (loading && newsList.value.isEmpty()) {
           LoadingComponent()
        } else if (!loading && error != "") {
            EmptyList(error)
        } else {

            LazyColumn {

                itemsIndexed(items = newsList.value) { index, article ->
                    onChangeArticleScrollPosition(index)
                    if ((index + 1) >= (page * AppConstant.PAGE_SIZE) && !loading) {
                        onNextPage()
                    }
                    Logger.d("itemss ${newsList.value.size}     $article")
                    article.let {
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