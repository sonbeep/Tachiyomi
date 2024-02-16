package com.example.tachiyomi.api

import com.example.tachiyomi.model.AllMovie
import com.example.tachiyomi.model.DetailMovie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {
    @GET("/3/movie/now_playing?api_key=e9e9d8da18ae29fc430845952232787c&language=en-US&page=1")
    fun getAllMovieFavorite(): Call<AllMovie>

    @GET("/3/movie/popular?api_key=e9e9d8da18ae29fc430845952232787c&language=en-US&page=1")
    fun getAllMoviePopular(): Call<AllMovie>

    @GET("/3/movie/upcoming?api_key=e9e9d8da18ae29fc430845952232787c&language=en-US&page=1")
    fun getAllMovieUpComing(): Call<AllMovie>

    @GET("/3/movie/top_rated?api_key=e9e9d8da18ae29fc430845952232787c&language=en-US&page=1")
    fun getAllMovieTopRate(): Call<AllMovie>

    @GET("/3/movie/{movie_id}?api_key=e9e9d8da18ae29fc430845952232787c&append_to_response=videos")
    fun getDetailMovie(
        @Query("movie_id") movieId: Int
    ) : Call<DetailMovie>
}