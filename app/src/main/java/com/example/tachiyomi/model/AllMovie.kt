package com.example.tachiyomi.model

import com.google.gson.annotations.SerializedName

data class AllMovie (
    @SerializedName("results") val allMovie: List<Movie>,
)
