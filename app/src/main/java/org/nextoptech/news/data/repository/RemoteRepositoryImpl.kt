package org.nextoptech.news.data.repository

import org.nextoptech.news.data.data_source.remote.NewsApiService
import org.nextoptech.news.data.data_source.remote.dto.Article
import org.nextoptech.news.data.data_source.remote.dto.NewsDto
import org.nextoptech.news.domain.repository.RemoteRepository

class RemoteRepositoryImpl(private val newsApiService: NewsApiService) : RemoteRepository {


    override suspend fun getNews(
        query: String,
        from: String,
        to: String,
        sortBy: String,
        page: Int
    ): NewsDto =
        newsApiService.getNews(query = query,
            from = from,
            to = to,
            sortBy = sortBy,
            page = page
        )
}