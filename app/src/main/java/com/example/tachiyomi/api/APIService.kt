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

//    @GET("/3/movie/933131?api_key=e9e9d8da18ae29fc430845952232787c&append_to_response=videos")
//    fun getDetailMovie() : Call<DetailMovie>

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


    @GET("/3/genre/movie/list?api_key=df15a04d63ef140e3fcf60ed11d270a1&language=vi")
    fun getGenre() : Call<ListTheLoai>

    @GET
    fun getSearch(@Url url: String) : Call<AllMovie>
}