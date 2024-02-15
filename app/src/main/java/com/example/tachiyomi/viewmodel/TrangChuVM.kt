package com.example.tachiyomi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tachiyomi.api.APIService
import com.example.tachiyomi.api.RetrofitClient
import com.example.tachiyomi.model.AllMovie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TrangChuVM : ViewModel() {
    val apiService = RetrofitClient.getClient()?.create(APIService::class.java)
    val topic = MutableLiveData<Int>()
    val bottomSheetStatus = MutableLiveData<Int>()
    val allMoviePopular = MutableLiveData<AllMovie>()
    val allMovieUpComing = MutableLiveData<AllMovie>()
    val allMovieTopRate = MutableLiveData<AllMovie>()


    fun getAllMoviePopular(){
        val call: Call<AllMovie> = apiService?.getAllMoviePopular()!!
        call.enqueue(object : Callback<AllMovie> {
            override fun onResponse(call: Call<AllMovie>, response: Response<AllMovie>) {
                if (response.isSuccessful){
                    allMoviePopular.value = response.body()
                }
            }

            override fun onFailure(call: Call<AllMovie>, t: Throwable) {
            }

        })
    }

    fun getAllMovieUpComing(){
        val call: Call<AllMovie> = apiService?.getAllMovieUpComing()!!
        call.enqueue(object : Callback<AllMovie> {
            override fun onResponse(call: Call<AllMovie>, response: Response<AllMovie>) {
                if (response.isSuccessful){
                    allMovieUpComing.value = response.body()
                }
            }

            override fun onFailure(call: Call<AllMovie>, t: Throwable) {
            }

        })
    }

    fun getAllMovieTopRate(){
        val call: Call<AllMovie> = apiService?.getAllMovieTopRate()!!
        call.enqueue(object : Callback<AllMovie> {
            override fun onResponse(call: Call<AllMovie>, response: Response<AllMovie>) {
                if (response.isSuccessful){
                    allMovieTopRate.value = response.body()
                }
            }

            override fun onFailure(call: Call<AllMovie>, t: Throwable) {
            }

        })
    }

}