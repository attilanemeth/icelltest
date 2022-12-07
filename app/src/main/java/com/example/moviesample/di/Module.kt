package com.example.moviesample.di

import com.example.moviesample.api.interceptors.ApiKeyInterceptor
import com.example.moviesample.api.service.TheMovieDbApi
import com.example.moviesample.repos.MovieRepository
import com.example.moviesample.repos.MovieRepositoryImpl
import com.example.moviesample.repos.PeopleRepository
import com.example.moviesample.repos.PeopleRepositoryImpl
import com.example.moviesample.usecase.GetPopularPeopleUseCase
import com.example.moviesample.usecase.GetUpcomingMovieUseCase
import com.example.moviesample.usecase.GetUpcomingMoviesAllUseCase
import com.example.moviesample.viewmodel.ExperimentalViewModel
import com.example.moviesample.viewmodel.PeopleViewModel
import com.example.moviesample.viewmodel.RandomViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val diModules = module {
    factory<MovieRepository> { MovieRepositoryImpl(get()) }
    factory<PeopleRepository> { PeopleRepositoryImpl(get()) }
    single { GetUpcomingMovieUseCase(get()) }
    single { GetPopularPeopleUseCase(get()) }
    single { GetUpcomingMoviesAllUseCase(get()) }
    factory { ApiKeyInterceptor() }
    factory { KoinProvider.provideRetrofit(get()) }
    factory { KoinProvider.provideOkHttpClient(get()) }
    factory { KoinProvider.provideMovieDbApi(get()) }
    factory {TheMovieDbApi(get()) }

    viewModel { RandomViewModel(get()) }
    viewModel { ExperimentalViewModel(get()) }
    viewModel { PeopleViewModel(get()) }
}
