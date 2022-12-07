package com.example.newsapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.newsapp.data.model.Article
import kotlinx.coroutines.flow.Flow


@Dao
interface ArticleDao {
    @Query("SELECT * FROM article_table ORDER BY id ASC")
    fun getSavedArticles(): Flow<List<Article>>

    @Insert
    suspend fun insert(article: Article)
}
