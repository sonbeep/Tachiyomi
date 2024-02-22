package com.example.tachiyomi.modul

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.example.tachiyomi.viewmodel.TrangChuVM
import com.example.tachiyomi.viewmodel.DaHoanThanhVM
import com.example.tachiyomi.viewmodel.DetailMovieVM
import com.example.tachiyomi.viewmodel.SearchVM
import org.koin.androidx.viewmodel.dsl.viewModel

val viewModelModule = module {
    singleOf(::TrangChuVM)
    singleOf(::DaHoanThanhVM)
    singleOf(::DetailMovieVM)
    singleOf(::SearchVM)


}