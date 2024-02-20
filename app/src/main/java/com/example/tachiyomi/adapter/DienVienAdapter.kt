package com.example.tachiyomi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.tachiyomi.databinding.ItemDienVienBinding
import com.example.tachiyomi.databinding.ItemTruyenBinding
import com.example.tachiyomi.model.DienVien
import com.example.tachiyomi.model.Movie

class DienVienAdapter(val context: Context, val list: List<DienVien>) : Adapter<DienVienAdapter.DienVienViewHolder>() {
    class DienVienViewHolder(val binding: ItemDienVienBinding) : ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DienVienViewHolder {
        val binding = ItemDienVienBinding.inflate(LayoutInflater.from(context), parent, false)
        return DienVienViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: DienVienViewHolder, position: Int) {
        val dienVien = list[position]
        Glide.with(context).load("https://image.tmdb.org/t/p/original/"+dienVien.profilePath).into(holder.binding.ivProfilePath)
        holder.binding.tvName.text = dienVien.name
    }
}
