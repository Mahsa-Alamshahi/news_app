package org.nextoptech.news.domain.repository

import org.nextoptech.news.data.data_source.remote.dto.NewsDto
import org.nextoptech.news.domain.model.News

interface LocalRepository {

    suspend fun getNewsList(): List<NewsDto>
    suspend fun getNewsDetails(): NewsDto
    suspend fun deleteNews(news: News)
}