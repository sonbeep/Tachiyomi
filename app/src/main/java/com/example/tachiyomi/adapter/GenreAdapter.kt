package com.example.tachiyomi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tachiyomi.databinding.ItemGenreBinding
import com.example.tachiyomi.model.TheLoai

class GenreAdapter(val context: Context, val list: List<TheLoai>): Adapter<GenreAdapter.GenreViewHolder>() {
    class GenreViewHolder(val binding: ItemGenreBinding): ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val binding = ItemGenreBinding.inflate(LayoutInflater.from(context), parent, false)
        return GenreViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size

    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {

    }
}