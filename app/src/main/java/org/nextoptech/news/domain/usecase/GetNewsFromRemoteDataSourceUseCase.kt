package org.nextoptech.news.domain.usecase

import com.orhanobut.logger.Logger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.nextoptech.news.common.Resource
import org.nextoptech.news.data.data_source.local.NewsEntity
import org.nextoptech.news.data.data_source.remote.dto.toNewsEntity
import org.nextoptech.news.domain.repository.RemoteRepository
import org.nextoptech.news.utils.QueryParams
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetNewsFromRemoteDataSourceUseCase @Inject constructor(private val remoteRepository: RemoteRepository) {


    suspend operator fun invoke(
        query: QueryParams,
        from: String,
        to: String,
        sortBy: String,
        page: Int
    ): Flow<Resource<List<NewsEntity>>> = flow {

        try {
            emit(Resource.Loading())
            val news =
                remoteRepository.getNews(query = query.name, from = from, to = to,
                    sortBy = sortBy, page = page)

            Logger.d("api resss $news")
            if (news.status == "ok") {

                emit(Resource.Success(news.articles.map {
                    it.toNewsEntity(queryName = query)
                }))
            } else if (news.status =="error") {
                emit(Resource.Error(news.message.toString()))
            }

        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured."))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't get data."))
        } catch (e: Exception){
            emit(Resource.Error(e.message.toString()))
        }

    }

}