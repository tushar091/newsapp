package com.example.byju.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.byju.EMPTY_STRING

@Entity(tableName = "Articles")
data class ArticlesTable(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "author") var author: String? = EMPTY_STRING,
    @ColumnInfo(name = "title") var title: String? = EMPTY_STRING,
    @ColumnInfo(name = "description") var description: String? = EMPTY_STRING,
    @ColumnInfo(name = "url") var url: String? = EMPTY_STRING,
    @ColumnInfo(name = "imageUrl") var urlToImage: String? = EMPTY_STRING,
    @ColumnInfo(name = "publishedDateTime") var publishedAt: String? = EMPTY_STRING,
    @ColumnInfo(name = "content") var content: String? = EMPTY_STRING,
    @ColumnInfo(name = "source") var source: String? = EMPTY_STRING
)