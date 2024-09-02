package org.nextoptech.news.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.nextoptech.news.common.Resource
import org.nextoptech.news.data.data_source.remote.dto.NewsDto
import org.nextoptech.news.domain.repository.RemoteRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(private val remoteRepository: RemoteRepository) {


    suspend operator fun invoke(
        query: String,
        from: String,
        to: String,
        sortBy: String
    ): Flow<Resource<NewsDto>> = flow {

        try {
            emit(Resource.Loading())
            val news =
                remoteRepository.getNews(query = query, from = from, to = to, sortBy = sortBy)
            emit(Resource.Success(news))

        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured."))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't get data."))
        }

    }

}