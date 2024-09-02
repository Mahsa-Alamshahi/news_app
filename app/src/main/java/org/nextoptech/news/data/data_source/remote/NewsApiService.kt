package org.nextoptech.news.data.data_source.remote

import org.nextoptech.news.common.AppConstant.API_KEY
import org.nextoptech.news.data.data_source.remote.dto.NewsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

@GET("everything")
suspend fun getNews(
    @Query("q") query: String,
    @Query("from") from: String,
    @Query("to") to: String,
    @Query("sortBy") sortBy: String,
    @Query("apiKey") apiKey: String = API_KEY
): NewsDto

}