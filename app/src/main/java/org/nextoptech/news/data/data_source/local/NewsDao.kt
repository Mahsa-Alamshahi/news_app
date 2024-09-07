package org.nextoptech.news.data.data_source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewsArticle(article: NewsEntity)

    @Insert
    fun addNewsList(newsList: List<NewsEntity>): List<Long>


    @Query("SELECT * FROM news")
    suspend fun getNewsList(): List<NewsEntity>

    @Query("DELETE FROM news")
    suspend fun deleteAllNews()


}