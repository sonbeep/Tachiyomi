package com.example.tachiyomi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tachiyomi.model.AllMovie
import com.example.tachiyomi.model.app_util.whenLeftRight
import com.example.tachiyomi.repository.HoanThanhRepository
import kotlinx.coroutines.launch

class DaHoanThanhVM(private val hoanThanhRepository: HoanThanhRepository) : ViewModel() {
    val allMovie = MutableLiveData<AllMovie>()
    val muc = MutableLiveData<Int>()

    fun getAllMovieFavorite() = viewModelScope.launch {
        val response = hoanThanhRepository.getAllMovieFavorite()
        response.whenLeftRight(
            {}, {
                allMovie.value = it
            }
        )
    }


}