package com.example.daggermvvmexample.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daggermvvmexample.data.model.ArticlesResponse
import com.example.daggermvvmexample.data.repository.ArticlesRepository
import com.example.daggermvvmexample.utils.NetworkHelper
import com.example.daggermvvmexample.utils.Resource
import kotlinx.coroutines.launch

class ArticlesViewModel @ViewModelInject constructor(
    private val articlesRepository: ArticlesRepository,
    private val networkHelper: NetworkHelper
) : ViewModel(){

    private val _articles = MutableLiveData<Resource<ArticlesResponse>>()
    val articles : LiveData<Resource<ArticlesResponse>>
        get() = _articles

    init {
        fetchArticles()
    }

    private fun fetchArticles() {
        viewModelScope.launch {
            _articles.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()){
                articlesRepository.getArticles().let {
                    if (it.isSuccessful){
                        _articles.postValue(Resource.success(it.body()))
                    }else {
                        _articles.postValue(Resource.error("No Internet Connection", null))
                    }
                }
            }
        }
    }
}