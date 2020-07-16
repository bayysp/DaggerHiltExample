package com.example.daggermvvmexample.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.daggermvvmexample.R
import com.example.daggermvvmexample.data.model.ArticlesResponse
import com.example.daggermvvmexample.ui.adapter.ArticlesAdapter
import com.example.daggermvvmexample.ui.viewmodel.ArticlesViewModel
import com.example.daggermvvmexample.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val articlesViewModel : ArticlesViewModel by viewModels()
    private lateinit var adapter : ArticlesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
        setupObserver()
    }

    private fun setupObserver() {
        articlesViewModel.articles.observe(this, Observer {
            when(it.status){
                Status.SUCCESS -> {
                    activitymain_progressbar.visibility = View.GONE
                    it.data?.let { articles -> renderList(articles) }
                    activitymain_rv_news.visibility = View.VISIBLE
                }

                Status.LOADING -> {
                    activitymain_progressbar.visibility = View.VISIBLE
                    activitymain_rv_news.visibility = View.GONE
                }

                Status.ERROR -> {
                    activitymain_progressbar.visibility = View.GONE
                    Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun renderList(listArticle: ArticlesResponse) {
        adapter.addData(listArticle.articles)
    }

    private fun setupUI() {
        activitymain_rv_news.layoutManager = LinearLayoutManager(this)
        adapter = ArticlesAdapter(arrayListOf())
        activitymain_rv_news.addItemDecoration(
            DividerItemDecoration(
                activitymain_rv_news.context,
                (activitymain_rv_news.layoutManager as LinearLayoutManager).orientation
            )
        )
        activitymain_rv_news.adapter = adapter
    }
}