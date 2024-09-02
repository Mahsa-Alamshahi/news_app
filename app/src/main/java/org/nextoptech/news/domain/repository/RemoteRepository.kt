package org.nextoptech.news.domain.repository

import org.nextoptech.news.data.data_source.remote.dto.NewsDto

interface RemoteRepository {

    suspend fun getNews(query: String, from: String, to: String, sortBy: String): NewsDto
}