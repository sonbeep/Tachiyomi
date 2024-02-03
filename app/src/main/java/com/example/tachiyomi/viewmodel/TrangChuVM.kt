package com.example.tachiyomi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TrangChuVM : ViewModel() {
    val topic = MutableLiveData<Int>()
    val bottomSheetStatus = MutableLiveData<Int>()
}