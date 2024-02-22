package com.example.tachiyomi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tachiyomi.model.AllMovie
import com.example.tachiyomi.model.app_util.whenLeftRight
import com.example.tachiyomi.repository.SearchRepository
import kotlinx.coroutines.launch


class SearchVM(private val searchRepository: SearchRepository) : ViewModel() {
    val searchResult = MutableLiveData<AllMovie>()

    fun getSearch(key: String) = viewModelScope.launch {
        val response = searchRepository.getSearch(key)
        response.whenLeftRight(
            {}, {
                searchResult.value = it
            }
        )
    }

}