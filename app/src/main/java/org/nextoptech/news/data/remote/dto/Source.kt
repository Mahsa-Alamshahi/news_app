package org.nextoptech.news.data.remote.dto

import androidx.compose.runtime.Stable
import com.google.gson.annotations.SerializedName

@Stable
data class Source(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?
)