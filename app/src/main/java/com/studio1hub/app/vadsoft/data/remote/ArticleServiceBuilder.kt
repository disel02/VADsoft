package com.studio1hub.app.vadsoft.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ArticleServiceBuilder{

    private const val URL = "http://disel.site/"

    fun provideRetrofitService():AppService = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(AppService::class.java)

}
