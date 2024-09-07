package org.nextoptech.news.domain.repository


import org.nextoptech.news.data.data_source.local.NewsEntity

interface LocalRepository {

    suspend fun getNewsList(): List<NewsEntity>
    suspend fun deleteNews()
    suspend fun addNewsList(newsList: List<NewsEntity>): List<Long>
}