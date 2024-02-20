package com.example.tachiyomi.model

import com.google.gson.annotations.SerializedName

data class Video (
    @SerializedName("results") val trailer : List<Trailer>

)