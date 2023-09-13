package com.example.news.ui.categories

import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.news.databinding.ItemCategoriesBinding

class CategoriesAdapter (val items : List<Categories>)
    :RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {
    class ViewHolder (val itemBinding:ItemCategoriesBinding)
        : RecyclerView.ViewHolder(itemBinding.root){
            fun bind(category: Categories){
                itemBinding.category = category
                itemBinding.invalidateAll()
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = ItemCategoriesBinding
            .inflate(LayoutInflater.from(parent.context),
                parent,
                false)
        return ViewHolder(viewBinding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemBinding.container.setCardBackgroundColor(
            ContextCompat.getColor(holder.itemView.context,
                items[position].bg_colorId)
        )
        holder.itemBinding.image.setImageResource(items[position].imageId)
        holder.bind(items[position])
        onItemCLickListener?.let {clickListener->
            holder.itemBinding.container.setOnClickListener{
                clickListener.onItemClick(position,items[position])
            }
        }
    }
    var onItemCLickListener : OnItemClickListener? =null
    interface OnItemClickListener{
        fun onItemClick(position: Int,item :Categories)
    }
}