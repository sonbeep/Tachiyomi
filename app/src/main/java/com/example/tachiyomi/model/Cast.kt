package com.example.tachiyomi.model

import com.google.gson.annotations.SerializedName

data class Cast (
    @SerializedName("cast") val cast : List<DienVien>,

)