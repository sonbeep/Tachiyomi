package com.example.tachiyomi.modul

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.example.tachiyomi.viewmodel.TrangChuVM
import com.example.tachiyomi.viewmodel.DaHoanThanhVM


val viewModelModule = module {
    singleOf(::TrangChuVM)
    singleOf(::DaHoanThanhVM)

}