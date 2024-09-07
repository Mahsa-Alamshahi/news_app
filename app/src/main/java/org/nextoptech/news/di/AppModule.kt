package org.nextoptech.news.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.nextoptech.news.data.data_source.local.NewsDao
import org.nextoptech.news.data.data_source.remote.NewsApiService
import org.nextoptech.news.data.repository.LocalRepositoryImpl
import org.nextoptech.news.data.repository.RemoteRepositoryImpl
import org.nextoptech.news.domain.repository.LocalRepository
import org.nextoptech.news.domain.repository.RemoteRepository
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideApplicationContext(@ApplicationContext applicationContext: Context): Context =
        applicationContext

    @Provides
    @Singleton
    fun provideRemoteRepository(newsApiService: NewsApiService): RemoteRepository =
        RemoteRepositoryImpl(newsApiService = newsApiService)


    @Provides
    @Singleton
    fun provideLocalRepository(newsDao: NewsDao): LocalRepository =
        LocalRepositoryImpl(newsDao = newsDao)

}