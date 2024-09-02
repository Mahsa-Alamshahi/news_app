package org.nextoptech.news.domain.model

import androidx.compose.runtime.Stable

@Stable
data class News(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val sourceId: String?,
    val sourceName: String?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
)
