package com.example.tachiyomi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.tachiyomi.databinding.ItemTruyenBinding
import com.example.tachiyomi.model.Movie

class TruyenAdapter(val context: Context, val list: List<Movie>) : Adapter<TruyenAdapter.TruyenViewHolder>() {
    class TruyenViewHolder(val binding: ItemTruyenBinding) : ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TruyenViewHolder {
        val binding = ItemTruyenBinding.inflate(LayoutInflater.from(context), parent, false)
        return TruyenViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TruyenViewHolder, position: Int) {
        val movie = list[position]
        Glide.with(context).load("https://image.tmdb.org/t/p/original/"+movie.posterPath).into(holder.binding.ivTruyen)
    }
}