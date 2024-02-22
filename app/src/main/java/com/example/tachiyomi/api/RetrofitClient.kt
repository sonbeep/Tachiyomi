package com.example.tachiyomi.api

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


fun providesApiService(
    factory: Gson,
    client: OkHttpClient,
): APIService {
    return Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org")
        .addConverterFactory(GsonConverterFactory.create(factory))
        .client(client)
        .build()
        .create(APIService::class.java)
}

fun providesBuildApiServiceHttpClient(
): OkHttpClient {
    val builder = OkHttpClient.Builder()
        .readTimeout(60000, TimeUnit.SECONDS)
        .writeTimeout(60000, TimeUnit.SECONDS)
        .connectTimeout(60000, TimeUnit.SECONDS)
        .callTimeout(60000, TimeUnit.SECONDS)

    return builder.build()
}