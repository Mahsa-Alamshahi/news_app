package org.nextoptech.news.ui.news_list

import androidx.compose.runtime.Stable
import org.nextoptech.news.data.data_source.local.NewsEntity
@Stable
data class NewsState(
    val isLoading: Boolean = false,
    val newsList: List<NewsEntity?>? = emptyList(),
    val error: String = ""
)
