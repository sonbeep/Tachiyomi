package com.example.tachiyomi.viewmodel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tachiyomi.api.APIService
import com.example.tachiyomi.api.RetrofitClient
import com.example.tachiyomi.model.AllMovie
import com.example.tachiyomi.model.Cast
import com.example.tachiyomi.model.DetailMovie
import com.example.tachiyomi.model.ListTheLoai
import com.example.tachiyomi.model.Movie
import com.example.tachiyomi.model.TheLoai
import com.example.tachiyomi.model.Trailer
import com.example.tachiyomi.model.Video
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailMovieVM: ViewModel() {
    val apiService = RetrofitClient.getClient()?.create(APIService::class.java)
    var detailMovie = MutableLiveData<DetailMovie>()
    var theLoai = MutableLiveData<ListTheLoai>()
    var mess = MutableLiveData<String>()
    var movieId = MutableLiveData<Int>()
    var listSimilarMovie = MutableLiveData<AllMovie>()
    var cast = MutableLiveData<Cast>()
    var keyVideo = MutableLiveData<String>()

    fun getDetailMovie(movieId: Int){
        val call: Call<DetailMovie> = apiService?.getDetailMovie("/3/movie/$movieId?api_key=e9e9d8da18ae29fc430845952232787c&append_to_response=videos")!!
        call.enqueue(object : Callback<DetailMovie>{
            override fun onResponse(call: Call<DetailMovie>, response: Response<DetailMovie>) {
                if (response.isSuccessful){
                    detailMovie.value = response.body()
                }else{

                }
            }

            override fun onFailure(call: Call<DetailMovie>, t: Throwable) {

            }

        })

    }

    fun getTheLoai(){
        val call : Call<ListTheLoai> = apiService?.getGenre()!!
        call.enqueue(object : Callback<ListTheLoai>{
            override fun onResponse(call: Call<ListTheLoai>, response: Response<ListTheLoai>) {
                if (response.isSuccessful){
                    theLoai.value = response.body()
                }else
                {
                    mess.value = "fail"
                }
            }

            override fun onFailure(call: Call<ListTheLoai>, t: Throwable) {
            }

        })
    }

    fun getListSimilarMovie(movieId: Int){
        val call: Call<AllMovie> = apiService?.getListSimilarMovie("/3/movie/$movieId/similar?api_key=e9e9d8da18ae29fc430845952232787c&language=en-US&page=1")!!
        call.enqueue(object : Callback<AllMovie>{
            override fun onResponse(call: Call<AllMovie>, response: Response<AllMovie>) {
                if (response.isSuccessful){
                    listSimilarMovie.value = response.body()
                }else{

                }
            }

            override fun onFailure(call: Call<AllMovie>, t: Throwable) {

            }

        })
    }

    fun getActor(movieId: Int){
        val call: Call<Cast> = apiService?.getActor("/3/movie/$movieId/casts?api_key=e9e9d8da18ae29fc430845952232787c")!!
        call.enqueue(object : Callback<Cast>{
            override fun onResponse(call: Call<Cast>, response: Response<Cast>) {
                if (response.isSuccessful){
                    cast.value = response.body()
                }else{

                }
            }

            override fun onFailure(call: Call<Cast>, t: Throwable) {

            }

        })
    }

}