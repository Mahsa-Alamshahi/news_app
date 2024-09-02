package org.nextoptech.news.data.repository

import org.nextoptech.news.data.data_source.local.NewsDao
import org.nextoptech.news.data.data_source.remote.dto.NewsDto
import org.nextoptech.news.domain.model.News
import org.nextoptech.news.domain.repository.LocalRepository
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(private val newsDao: NewsDao): LocalRepository {


    override suspend fun getNewsList(): List<NewsDto> {
        TODO("Not yet implemented")
    }



    override suspend fun getNewsDetails(): NewsDto {
        TODO("Not yet implemented")
    }



    override suspend fun deleteNews(news: News) {
        TODO("Not yet implemented")
    }
}