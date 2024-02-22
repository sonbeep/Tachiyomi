package com.example.tachiyomi.modul

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.example.tachiyomi.api.NetworkService
import com.example.tachiyomi.api.NetworkServiceImpl
import com.example.tachiyomi.api.providesApiService
import com.example.tachiyomi.api.providesBuildApiServiceHttpClient
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.koin.core.module.dsl.bind

val networkModule = module {
    single { provideGson() }
    singleOf(::NetworkServiceImpl) { bind<NetworkService>() }

    single { providesBuildApiServiceHttpClient() }
    single { providesApiService(get(), get()) }
}

fun provideGson(): Gson {
    return GsonBuilder().create()
}
