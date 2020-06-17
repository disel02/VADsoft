package com.studio1hub.app.vadsoft.data.local.article

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.studio1hub.app.vadsoft.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticles(posts: List<Article>)

    @Query("DELETE FROM Article")
    fun deleteArticles()

    @Query("SELECT * FROM Article WHERE id = :articleId")
    fun getArticleById(articleId: Int): Flow<Article>

    @Query("SELECT * FROM Article")
    fun getAllArticles(): Flow<List<Article>>

}
