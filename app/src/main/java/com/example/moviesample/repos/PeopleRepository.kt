package com.example.moviesample.repos

import com.example.moviesample.api.model.PeopleResponse
import com.example.moviesample.api.service.TheMovieDbApi

interface PeopleRepository {
    suspend fun getPopularPeoples(): PeopleResponse
}

class PeopleRepositoryImpl(private val api: TheMovieDbApi) : PeopleRepository {
    override suspend fun getPopularPeoples() = api.endpoints.fetchPopularPeople(1)
}