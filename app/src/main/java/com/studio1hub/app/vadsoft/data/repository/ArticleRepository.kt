package com.studio1hub.app.vadsoft.data.repository

import androidx.annotation.MainThread
import com.studio1hub.app.vadsoft.data.local.article.ArticleDao
import com.studio1hub.app.vadsoft.data.remote.AppService
import com.studio1hub.app.vadsoft.model.Article
import com.studio1hub.app.vadsoft.utils.State
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

class ArticleRepository(val articleDao: ArticleDao, val appService: AppService) {

    fun getArticleRepo(): Flow<State<List<Article>>> {
        return object: NetworkBoundRepository<List<Article>, List<Article>>(){

            override suspend fun fetchFromRemote(): Response<List<Article>> = appService.getArticles()

            override suspend fun saveRemoteData(response: List<Article>) = articleDao.insertArticles(response)

            override fun fetchFromLocal(): Flow<List<Article>> = articleDao.getAllArticles()

        }.asFlow().flowOn(IO)
    }

    @MainThread
    fun getPostById(articleId: Int): Flow<Article> = articleDao.getArticleById(articleId)
}