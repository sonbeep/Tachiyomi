package com.example.tachiyomi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.tachiyomi.R
import com.example.tachiyomi.model.Banner

class BannerAdapter(private val listImage : ArrayList<Banner>) : RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {
    class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val image = itemView.findViewById<ImageView>(R.id.ivBanner)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_banner, parent, false)
        return BannerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listImage.size
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        val banner = listImage[position]
        holder.image.setImageResource(banner.image)
    }
}