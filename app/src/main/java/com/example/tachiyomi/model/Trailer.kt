package com.example.tachiyomi.model

import com.google.gson.annotations.SerializedName

data class Trailer (
    @SerializedName("key") val key : String,
    @SerializedName("type") val type : String

)