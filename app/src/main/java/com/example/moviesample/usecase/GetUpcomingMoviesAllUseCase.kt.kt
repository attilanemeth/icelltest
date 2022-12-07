package com.example.moviesample.usecase

import android.util.Log
import com.example.moviesample.api.model.UpcomingMovie
import com.example.moviesample.repos.MovieRepository
import kotlinx.coroutines.flow.flow

class GetUpcomingMoviesAllUseCase(private val movieRepository: MovieRepository) {

     operator fun invoke() = flow<List<UpcomingMovie>> {
         emit(emptyList<UpcomingMovie>())
            try {
                val response = movieRepository.getLatestMovies()
                emit(response.results)
            } catch (ex: Exception) {
                Log.e("NETWORK EX", ex.message, ex)
                emit(emptyList<UpcomingMovie>())
            }
    }
}
