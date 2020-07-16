package com.example.daggermvvmexample.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.daggermvvmexample.R
import com.example.daggermvvmexample.data.model.ArticlesItem
import kotlinx.android.synthetic.main.item_articles.view.*

class ArticlesAdapter (
     private val articles : ArrayList<ArticlesItem>
 ) : RecyclerView.Adapter<ArticlesAdapter.DataViewHolder>() {

    class DataViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind(article : ArticlesItem){
            itemView.itemarticles_tv_title.text = article.title
            Glide.with(itemView.itemarticles_iv_thumb.context)
                .load(article.urlToImage)
                .error(R.drawable.image_broken)
                .into(itemView.itemarticles_iv_thumb)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArticlesAdapter.DataViewHolder {
        return DataViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_articles,parent,false)
        )
    }

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: ArticlesAdapter.DataViewHolder, position: Int) {
        return holder.bind(articles[position])
    }

    fun addData(list : List<ArticlesItem>){
        articles.clear()
        articles.addAll(list)
        notifyDataSetChanged()
    }


}
