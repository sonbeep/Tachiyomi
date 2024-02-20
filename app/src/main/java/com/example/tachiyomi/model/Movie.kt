package com.example.tachiyomi.model

import com.google.gson.annotations.SerializedName

data class Movie (
    @SerializedName("original_title") val originalTitle : String,
    @SerializedName("popularity") val popularity : String,
    @SerializedName("poster_path") val posterPath : String,
    @SerializedName("release_date") val releaseDate : String,
    @SerializedName("backdrop_path") val backdropPath : String,
    @SerializedName("title") val title : String,
    @SerializedName("vote_count") val voteCount : Int,
    @SerializedName("id") val id : Int,
    @SerializedName("overview") val overview : String,

)