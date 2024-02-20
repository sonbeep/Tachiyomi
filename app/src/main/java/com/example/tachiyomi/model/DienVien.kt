package com.example.tachiyomi.model

import com.google.gson.annotations.SerializedName

data class DienVien (
    @SerializedName("name") val name : String,
    @SerializedName("profile_path") val profilePath : String,
    )