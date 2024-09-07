package org.nextoptech.news.data.data_source.remote.dto

import androidx.compose.runtime.Stable
import com.google.gson.annotations.SerializedName

@Stable
data class Source(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?
)