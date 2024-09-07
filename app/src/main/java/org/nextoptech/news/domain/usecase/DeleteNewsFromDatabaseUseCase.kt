package org.nextoptech.news.domain.usecase


import org.nextoptech.news.domain.repository.LocalRepository
import javax.inject.Inject

class DeleteNewsFromDatabaseUseCase @Inject constructor(private val localRepository: LocalRepository){

    suspend operator fun invoke() = localRepository.deleteNews()
}