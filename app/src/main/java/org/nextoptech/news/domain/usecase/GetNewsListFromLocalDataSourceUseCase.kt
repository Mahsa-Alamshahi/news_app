package org.nextoptech.news.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.nextoptech.news.common.Resource
import org.nextoptech.news.data.data_source.local.NewsEntity
import org.nextoptech.news.domain.repository.LocalRepository
import java.io.IOException

import javax.inject.Inject

class GetNewsListFromLocalDataSourceUseCase @Inject constructor(private val localRepository: LocalRepository) {

    suspend operator fun invoke(): Flow<Resource<List<NewsEntity>>> = flow {
        try {
            emit(Resource.Loading())
            val newsList = localRepository.getNewsList()
            emit(Resource.Success(newsList))
        } catch (e: IOException) {
            emit(Resource.Error("An unexpected error occured."))
        } catch (e: Exception) {
            emit(Resource.Error("An unexpected error occured."))
        }

    }
}