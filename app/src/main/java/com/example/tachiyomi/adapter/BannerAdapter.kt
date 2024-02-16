package com.example.tachiyomi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tachiyomi.R
import com.example.tachiyomi.databinding.ItemBannerBinding
import com.example.tachiyomi.model.Banner
import com.example.tachiyomi.model.Movie

class BannerAdapter(private val context: Context, private val listImage : ArrayList<Movie>) : RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {
    class BannerViewHolder(val binding: ItemBannerBinding) : RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val binding = ItemBannerBinding.inflate(LayoutInflater.from(context), parent, false)
        return BannerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listImage.size
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        val movie = listImage[position]
        Glide.with(context).load("https://image.tmdb.org/t/p/w500"+movie.posterPath).into(holder.binding.ivBanner)

    }
}