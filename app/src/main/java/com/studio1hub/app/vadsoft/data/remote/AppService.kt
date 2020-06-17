package com.studio1hub.app.vadsoft.data.remote

import com.studio1hub.app.vadsoft.model.Article
import com.studio1hub.app.vadsoft.model.User
import retrofit2.Response
import retrofit2.http.GET

interface AppService {

    @GET("/json/article")
    suspend fun getArticles(): Response<List<Article>>

    @GET("/jet2/api/v1/users?page=1&limit=10")
    suspend fun getUsers(): Response<List<User>>

}
