package com.example.news.ui.browser

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news.databinding.ItemArticleBinding
import com.example.news.model.Article

class ArticlesAdapter(var items:List<Article>?) :RecyclerView.Adapter<ArticlesAdapter.viewHolder>(){

    class viewHolder(val viewBinding:ItemArticleBinding) :RecyclerView.ViewHolder(viewBinding.root){
        fun bind(article: Article?){
            viewBinding.article = article
            viewBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val viewBinding = ItemArticleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return viewHolder(viewBinding)
    }

    override fun getItemCount(): Int = items?.size?:0

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.bind(items?.get(position) )
        Glide.with(holder.itemView)
            .load(items?.get(position)?.urlToImage)
            .into(holder.viewBinding.igArticle)
    }

    fun changeData(newList : List<Article>?){
        items =newList
        notifyDataSetChanged()

    }
}