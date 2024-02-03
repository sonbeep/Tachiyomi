package com.example.tachiyomi

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.example.tachiyomi.viewmodel.TrangChuVM

val viewModelModule = module {
    singleOf(::TrangChuVM)
}