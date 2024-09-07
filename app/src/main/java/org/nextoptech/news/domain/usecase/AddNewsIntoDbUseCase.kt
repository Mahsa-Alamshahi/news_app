package org.nextoptech.news.domain.usecase

import org.nextoptech.news.data.data_source.local.NewsEntity
import org.nextoptech.news.domain.repository.LocalRepository
import javax.inject.Inject

class AddNewsIntoDbUseCase @Inject constructor(private val localRepository: LocalRepository) {

    suspend operator fun invoke(newsList: List<NewsEntity>): List<Long> =
        localRepository.addNewsList(newsList)

}