package org.nextoptech.news.data.data_source.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.nextoptech.news.utils.QueryParams


@Entity(tableName = "news")
data class NewsEntity(

    @ColumnInfo(name = "author") var author: String? = null,
    @ColumnInfo(name = "content") var content: String? = null,
    @ColumnInfo(name = "description") var description: String? = null,
    @ColumnInfo(name = "published_at") var publishedAt: String? = null,
    @ColumnInfo(name = "title") var title: String? = null,
    @ColumnInfo(name = "url") var url: String? = null,
    @ColumnInfo(name = "image_url") var imageUrl: String? = null,
    @ColumnInfo(name = "source_name") var sourceName: String? = null,
    @ColumnInfo(name = "source_id") var sourceId: String? = null,
    @ColumnInfo(name = "query_name") var queryName: QueryParams? = null,

    ) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}

