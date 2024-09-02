package org.nextoptech.news.data.remote.dto

import androidx.compose.runtime.Stable
import com.google.gson.annotations.SerializedName

@Stable
data class NewsApiResponse(
    @SerializedName("articles")
    val articles: List<Article?>?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("totalResults")
    val totalResults: Int?
)