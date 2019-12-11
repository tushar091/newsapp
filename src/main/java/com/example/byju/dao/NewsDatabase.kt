package com.example.byju.dao

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ArticlesTable::class], version = 4)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun articlesDao(): ArticlesDao
}