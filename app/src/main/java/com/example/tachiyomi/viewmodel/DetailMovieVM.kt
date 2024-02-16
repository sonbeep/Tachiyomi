package com.example.tachiyomi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tachiyomi.api.APIService
import com.example.tachiyomi.api.RetrofitClient
import com.example.tachiyomi.model.DetailMovie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailMovieVM: ViewModel() {
    val apiService = RetrofitClient.getClient()?.create(APIService::class.java)
    var detailMovie = MutableLiveData<DetailMovie>()


    fun getDetailMovie(movieId : Int){
        val call: Call<DetailMovie> = apiService?.getDetailMovie(movieId)!!
        call.enqueue(object : Callback<DetailMovie>{
            override fun onResponse(call: Call<DetailMovie>, response: Response<DetailMovie>) {
                if (response.isSuccessful){
                    detailMovie.value = response.body()
                }
            }

            override fun onFailure(call: Call<DetailMovie>, t: Throwable) {

            }

        })

    }
}