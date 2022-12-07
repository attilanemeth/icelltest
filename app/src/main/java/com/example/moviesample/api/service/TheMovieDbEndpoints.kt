package com.example.moviesample.api.service

import com.example.moviesample.api.model.PeopleResponse
import com.example.moviesample.api.model.UpcomingResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieDbEndpoints {

    @GET("movie/upcoming")
    suspend fun getLatestAsync(): UpcomingResponse


    @GET("person/popular?language=en")
    suspend fun fetchPopularPeople(@Query("page") page: Int): PeopleResponse
}