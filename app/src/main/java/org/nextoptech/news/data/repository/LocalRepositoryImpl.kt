package org.nextoptech.news.data.repository

import org.nextoptech.news.data.data_source.local.NewsDao
import org.nextoptech.news.data.data_source.local.NewsEntity
import org.nextoptech.news.domain.repository.LocalRepository
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(private val newsDao: NewsDao): LocalRepository {


    override suspend fun getNewsList(): List<NewsEntity> = newsDao.getNewsList()

    override suspend fun deleteNews() = newsDao.deleteAllNews()
    override suspend fun addNewsList(newsList: List<NewsEntity>): List<Long> =
        newsDao.addNewsList(newsList)

}