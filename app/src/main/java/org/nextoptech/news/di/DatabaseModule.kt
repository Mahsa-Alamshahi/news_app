package org.nextoptech.news.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.nextoptech.news.common.AppConstant.DB_NAME
import org.nextoptech.news.data.data_source.local.NewsDatabase
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {



    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext application: Context): NewsDatabase {
        return Room.databaseBuilder(application, NewsDatabase::class.java, DB_NAME)
            .allowMainThreadQueries().build()
    }


    @Singleton
    @Provides
    fun provideNewsDao(db: NewsDatabase) = db.newsDao()


}