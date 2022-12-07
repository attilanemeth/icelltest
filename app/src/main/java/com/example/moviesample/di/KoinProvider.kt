package com.example.moviesample.di

import com.example.moviesample.api.interceptors.ApiKeyInterceptor
import com.example.moviesample.api.service.TheMovieDbEndpoints
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object KoinProvider {
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return  Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    fun provideOkHttpClient(apiKeyInterceptor: ApiKeyInterceptor): OkHttpClient {
        return OkHttpClient().newBuilder().addInterceptor(apiKeyInterceptor).build()
    }

    fun provideMovieDbApi(retrofit: Retrofit): TheMovieDbEndpoints = retrofit.create(TheMovieDbEndpoints::class.java)
}