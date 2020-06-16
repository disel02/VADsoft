package com.studio1hub.app.vadsoft.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder{

    private const val URL = "https://5e99a9b1bc561b0016af3540.mockapi.io"

    fun provideRetrofitService():UserService = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(UserService::class.java)

}
