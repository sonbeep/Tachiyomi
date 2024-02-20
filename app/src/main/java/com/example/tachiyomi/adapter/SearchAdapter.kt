package com.example.tachiyomi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.tachiyomi.databinding.ItemSearchBinding
import com.example.tachiyomi.model.Movie

class SearchAdapter(val context: Context, val list : List<Movie>, val callBack: SearchToDetail) : Adapter<SearchAdapter.SearchViewHolder>() {
    class SearchViewHolder(val binding: ItemSearchBinding) : ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = ItemSearchBinding.inflate(LayoutInflater.from(context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val search = list[position]
        holder.binding.title.text = search.title
        holder.binding.releaseDate.text = search.releaseDate
        holder.binding.overview.text = search.overview
        Glide.with(context).load("https://image.tmdb.org/t/p/original/"+search.posterPath).into(holder.binding.ivAvt)
        holder.binding.llContainer.setOnClickListener {
            callBack.fromSearchToDetail(search.id)
        }
    }
}

interface SearchToDetail{
    fun fromSearchToDetail(movieId: Int)
}
