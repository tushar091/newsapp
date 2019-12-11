package com.example.byju.dao

import androidx.room.*
import com.example.byju.model.Articles

@Dao
interface ArticlesDao {
    @Query("Select * from articles")
    fun getArticles(): List<ArticlesTable>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticle(articles: ArticlesTable)

    @Query("Delete from articles")
    fun deleteArticles()
}