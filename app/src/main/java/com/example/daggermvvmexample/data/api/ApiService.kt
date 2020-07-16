package com.example.daggermvvmexample.data.api

import com.example.daggermvvmexample.data.model.ArticlesResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    //if there is any query param, you can add @GET annotation here
    @GET("v2/top-headlines?country=us&category=business&apiKey=6f283c80f77041959264d9e1f0734849")
    suspend fun getArticles() : Response<ArticlesResponse>
}