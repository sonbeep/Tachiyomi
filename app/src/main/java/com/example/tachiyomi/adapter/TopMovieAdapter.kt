package com.example.tachiyomi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.tachiyomi.R
import com.example.tachiyomi.databinding.ItemSearchBinding
import com.example.tachiyomi.databinding.ItemTopMovieBinding
import com.example.tachiyomi.model.Movie

class TopMovieAdapter(val context: Context, val list : List<Movie>, val callBack: TopToDetail) : Adapter<TopMovieAdapter.TopMovieViewHolder>() {
    class TopMovieViewHolder(val binding: ItemTopMovieBinding) : ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopMovieViewHolder {
        val binding = ItemTopMovieBinding.inflate(LayoutInflater.from(context), parent, false)
        return TopMovieViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TopMovieViewHolder, position: Int) {
        val search = list[position]
        holder.binding.title.text = search.title
        holder.binding.releaseDate.text = search.releaseDate
        holder.binding.overview.text = search.overview
        Glide.with(context).load("https://image.tmdb.org/t/p/original/"+search.posterPath).into(holder.binding.ivAvt)
        holder.binding.llContainer.setOnClickListener {
            callBack.fromTopToDetail(search.id)
        }
//        if (position == 0){
//            holder.binding.ivTop.setImageResource(R.drawable.ic_top1)
//        }else if (position == 1){
//            holder.binding.ivTop.setImageResource(R.drawable.ic_top2)
//        }else if (position == 2){
//            holder.binding.ivTop.setImageResource(R.drawable.ic_top3)
//        }else{
//            holder.binding.ivTop.visibility = View.GONE
//        }
    }
}

interface TopToDetail{
    fun fromTopToDetail(movieId: Int)
}
