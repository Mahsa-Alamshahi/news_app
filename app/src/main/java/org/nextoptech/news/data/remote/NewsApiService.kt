package org.nextoptech.news.data.remote

import org.nextoptech.news.data.remote.dto.NewsApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

@GET("everything")
suspend fun getNews(
    @Query("q") query: String,
    @Query("from") from: String,
    @Query("to") to: String,
    @Query("sortBy") sortBy: String,
    @Query("apiKey") apiKey: String
): NewsApiResponse

}