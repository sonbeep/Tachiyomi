package com.example.tachiyomi.model

import com.google.gson.annotations.SerializedName

data class TheLoai (
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name: String,
    )
