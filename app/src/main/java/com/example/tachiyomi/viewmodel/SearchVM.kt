package com.example.tachiyomi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tachiyomi.api.APIService
import com.example.tachiyomi.api.RetrofitClient
import com.example.tachiyomi.model.AllMovie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchVM: ViewModel() {
    val apiService = RetrofitClient.getClient()?.create(APIService::class.java)
    val searchResult = MutableLiveData<AllMovie>()


    fun getSearch(key: String){
        val call : Call<AllMovie> = apiService?.getSearch("/3/search/movie?api_key=df15a04d63ef140e3fcf60ed11d270a1&query=$key&page=1")!!
        call.enqueue(object : Callback<AllMovie>{
            override fun onResponse(call: Call<AllMovie>, response: Response<AllMovie>) {
                if (response.isSuccessful){
                    searchResult.value = response.body()
                }
            }

            override fun onFailure(call: Call<AllMovie>, t: Throwable) {

            }

        })
    }
}