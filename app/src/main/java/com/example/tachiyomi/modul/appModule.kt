package com.example.tachiyomi.modul

import org.koin.dsl.module

val appModule = module {
    includes(repositoryModule)
    includes(viewModelModule)
    includes(networkModule)
}