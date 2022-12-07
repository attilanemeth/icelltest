package com.example.moviesample.usecase

import android.util.Log
import com.example.moviesample.api.model.UpcomingMovie
import com.example.moviesample.repos.MovieRepository
import kotlinx.coroutines.flow.flow

class GetUpcomingMovieUseCase(private val movieRepository: MovieRepository) {

    //Simple cache DO NOT USE
   private val cache = mutableListOf<UpcomingMovie>()

     operator fun invoke() = flow {
        emit(State.Loading)
        if (cache.isEmpty()) {
            try {
                val response = movieRepository.getLatestMovies()
                cache.addAll(response.results)
                emit(State.Success(cache.shuffled().first()))
            } catch (ex: Exception) {
                Log.e("NETWORK EX", ex.message, ex)
                emit(State.Error)
            }
        } else {
            emit(State.Success(cache.shuffled().first()))
        }
    }
}

sealed class State {
    object Loading : State()
    data class Success(val movie:UpcomingMovie) : State()
    object Error : State()

}