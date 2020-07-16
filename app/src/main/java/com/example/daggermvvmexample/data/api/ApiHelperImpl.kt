package com.example.daggermvvmexample.data.api

import com.example.daggermvvmexample.data.model.ArticlesResponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper{

    override suspend fun getArticles(): Response<ArticlesResponse> {
        return apiService.getArticles()
    }
}