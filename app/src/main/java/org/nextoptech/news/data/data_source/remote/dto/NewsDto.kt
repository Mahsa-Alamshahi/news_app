package org.nextoptech.news.data.data_source.remote.dto

import androidx.compose.runtime.Stable
import com.google.gson.annotations.SerializedName
import org.nextoptech.news.domain.model.News

@Stable
data class NewsDto(
    @SerializedName("articles")
    val articles: List<Article?>?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("totalResults")
    val totalResults: Int?
)


