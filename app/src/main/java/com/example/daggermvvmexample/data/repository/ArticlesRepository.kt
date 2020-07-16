package com.example.daggermvvmexample.data.repository

import com.example.daggermvvmexample.data.api.ApiHelper
import javax.inject.Inject

class ArticlesRepository @Inject constructor(private val apiHelper: ApiHelper) {
    suspend fun getArticles() = apiHelper.getArticles()
}