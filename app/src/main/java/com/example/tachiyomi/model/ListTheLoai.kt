package com.example.tachiyomi.model

import com.google.gson.annotations.SerializedName

data class ListTheLoai (
    @SerializedName("genres") val genres : List<TheLoai>
)