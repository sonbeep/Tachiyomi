package com.example.tachiyomi.modul

import com.example.tachiyomi.repository.HoanThanhRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.example.tachiyomi.repository.TrangChuRepository
import com.example.tachiyomi.repository.TrangChuRepositoryImpl
import com.example.tachiyomi.repository.HoanThanhRepositoryImpl
import com.example.tachiyomi.repository.DetailMovieRepositoryImpl
import com.example.tachiyomi.repository.DetailMovieRepository
import com.example.tachiyomi.repository.SearchRepositoryImpl
import com.example.tachiyomi.repository.SearchRepository


import org.koin.core.module.dsl.bind


val repositoryModule = module {
    singleOf(::TrangChuRepositoryImpl) { bind<TrangChuRepository>() }
    singleOf(::HoanThanhRepositoryImpl) { bind<HoanThanhRepository>() }
    singleOf(::DetailMovieRepositoryImpl) { bind<DetailMovieRepository>() }
    singleOf(::SearchRepositoryImpl) { bind<SearchRepository>() }


}