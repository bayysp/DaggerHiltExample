package com.example.daggermvvmexample.data.api

import com.example.daggermvvmexample.data.model.ArticlesResponse
import retrofit2.Response

interface ApiHelper {

    suspend fun getArticles(): Response<ArticlesResponse>

}