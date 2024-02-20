package com.example.tachiyomi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tachiyomi.api.APIService
import com.example.tachiyomi.api.RetrofitClient
import com.example.tachiyomi.model.AllMovie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DaHoanThanhVM : ViewModel() {
    val apiService = RetrofitClient.getClient()?.create(APIService::class.java)
    val topic = MutableLiveData<Int>()
    val allMovie = MutableLiveData<AllMovie>()
    val mess = MutableLiveData<String>()
    val muc = MutableLiveData<Int>()

    fun getAllMovieFavorite(){
        val call: Call<AllMovie> = apiService?.getAllMovieFavorite()!!
        call.enqueue(object : Callback<AllMovie>{
            override fun onResponse(call: Call<AllMovie>, response: Response<AllMovie>) {
                if (response.isSuccessful){
                    allMovie.value = response.body()
                }
            }

            override fun onFailure(call: Call<AllMovie>, t: Throwable) {
                    mess.value = t.message
            }

        })
    }


}