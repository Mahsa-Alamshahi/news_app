package org.nextoptech.news.data.data_source.remote.dto

import androidx.compose.runtime.Stable
import com.google.gson.annotations.SerializedName
import org.nextoptech.news.data.data_source.local.NewsEntity
import org.nextoptech.news.utils.QueryParams

@Stable
data class Article(
    @SerializedName("author")
    val author: String?,
    @SerializedName("content")
    val content: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("publishedAt")
    val publishedAt: String?,
    @SerializedName("source")
    val source: Source?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("urlToImage")
    val urlToImage: String?
)




fun Article.toNewsEntity(queryName: QueryParams): NewsEntity {
    return NewsEntity(
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        title = title,
        url = url,
        imageUrl = urlToImage,
        sourceName = source?.name,
        sourceId = source?.id,
        queryName = queryName
    )
}