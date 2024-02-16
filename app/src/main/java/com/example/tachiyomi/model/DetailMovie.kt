package com.example.tachiyomi.model

import com.google.gson.annotations.SerializedName

data class DetailMovie (
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("genres") val genres: List<TheLoai>,
    @SerializedName("overview") val overview: String,
    @SerializedName("title") val title: String,
    @SerializedName("vote_average") val voteAverage: String,
    @SerializedName("vote_count") val voteCount: String,
    )