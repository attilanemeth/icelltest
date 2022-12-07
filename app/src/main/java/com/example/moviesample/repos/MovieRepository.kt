package com.example.moviesample.repos

import com.example.moviesample.api.model.UpcomingResponse
import com.example.moviesample.api.service.TheMovieDbApi

interface MovieRepository {

    suspend fun getLatestMovies():UpcomingResponse
}

class MovieRepositoryImpl(private val api:TheMovieDbApi): MovieRepository {
    override suspend fun getLatestMovies() = api.endpoints.getLatestAsync()
}