package com.example.tachiyomi.api

import com.example.tachiyomi.model.AllMovie
import com.example.tachiyomi.model.Cast
import com.example.tachiyomi.model.DetailMovie
import com.example.tachiyomi.model.ListTheLoai
import com.example.tachiyomi.model.TheLoai
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface APIService {
    @GET("/3/trending/movie/day?api_key=e9e9d8da18ae29fc430845952232787c&language=vi")
    fun getAllMovieFavorite(): Call<AllMovie>

    @GET("/3/movie/popular?api_key=e9e9d8da18ae29fc430845952232787c&language=vi&page=1")
    fun getAllMoviePopular(): Call<AllMovie>

    @GET("/3/movie/upcoming?api_key=e9e9d8da18ae29fc430845952232787c&language=vi&page=1")
    fun getAllMovieUpComing(): Call<AllMovie>

    @GET("/3/movie/top_rated?api_key=e9e9d8da18ae29fc430845952232787c&language=vi&page=1")
    fun getAllMovieTopRate(): Call<AllMovie>



    @GET
    fun getDetailMovie(
        @Url url: String
    ) : Call<DetailMovie>

    @GET
    fun getListSimilarMovie(
        @Url url: String
    ) : Call<AllMovie>

    @GET
    fun getActor(
        @Url url: String
    ) : Call<Cast>

    @GET
    fun getSearch(@Url url: String) : Call<AllMovie>
}