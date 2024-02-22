package com.example.tachiyomi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tachiyomi.model.AllMovie
import com.example.tachiyomi.model.app_util.whenLeftRight
import com.example.tachiyomi.repository.TrangChuRepository
import kotlinx.coroutines.launch

class TrangChuVM(private val trangChuRepository: TrangChuRepository) : ViewModel() {
    val topic = MutableLiveData<Int>()
    val bottomSheetStatus = MutableLiveData<Int>()
    val allMoviePopular = MutableLiveData<AllMovie>()
    val allMovieUpComing = MutableLiveData<AllMovie>()
    val allMovieTopRate = MutableLiveData<AllMovie>()


    fun getAllMoviePopular() = viewModelScope.launch {
        val response = trangChuRepository.getAllMoviePopular()
        response.whenLeftRight(
            {}, {
                allMoviePopular.value = it
            }
        )
    }
    fun getAllMovieUpComing() = viewModelScope.launch {
        val response = trangChuRepository.getAllMovieUpComing()
        response.whenLeftRight(
            {}, {
                allMovieUpComing.value = it
            }
        )
    }
    fun getAllMovieTopRate() = viewModelScope.launch {
        val response = trangChuRepository.getAllMovieTopRate()
        response.whenLeftRight(
            {}, {
                allMovieTopRate.value = it
            }
        )
    }

}