package org.nextoptech.news.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [NewsEntity::class], version = 1, exportSchema = false)
abstract class NewsDatabase: RoomDatabase() {

    abstract fun newsDao(): NewsDao
}