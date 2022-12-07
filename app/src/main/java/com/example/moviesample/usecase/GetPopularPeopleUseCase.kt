package com.example.moviesample.usecase

import com.example.moviesample.api.model.Person
import com.example.moviesample.repos.PeopleRepository
import kotlinx.coroutines.flow.flow

class GetPopularPeopleUseCase(private val peopleRepository: PeopleRepository) {
    operator fun invoke() = flow {
        try {
            val response = peopleRepository.getPopularPeoples()
            emit(response.results)
        } catch (ex: Exception) {
            emit(emptyList<Person>())
        }
    }
}