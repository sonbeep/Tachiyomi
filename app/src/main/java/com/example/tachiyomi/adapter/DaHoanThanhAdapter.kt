package com.example.tachiyomi.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.tachiyomi.databinding.ItemHoanThanhBinding
import com.example.tachiyomi.model.Movie

class DaHoanThanhAdapter(val context: Context, val list: List<Movie>): Adapter<DaHoanThanhAdapter.DaHoanThanhViewHolder>() {
    class DaHoanThanhViewHolder(val binding: ItemHoanThanhBinding) : ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaHoanThanhViewHolder {
        val binding = ItemHoanThanhBinding.inflate(LayoutInflater.from(context), parent, false)
        return DaHoanThanhViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: DaHoanThanhViewHolder, position: Int) {
        val movie = list[position]
        holder.binding.title.text = movie.title
        holder.binding.tvView.text = movie.voteCount.toString()
        holder.binding.tvView2.text = movie.voteCount.toString()
//        holder.binding.poster.setImageURI(Uri.parse("https://image.tmdb.org/t/p/original/"+movie.posterPath))
        Glide.with(context).load("https://image.tmdb.org/t/p/original/"+movie.posterPath).into(holder.binding.poster)

    }
}