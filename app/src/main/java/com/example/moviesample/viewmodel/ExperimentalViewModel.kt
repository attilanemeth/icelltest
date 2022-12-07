package com.example.moviesample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesample.api.model.UpcomingMovie
import com.example.moviesample.usecase.GetUpcomingMoviesAllUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ExperimentalViewModel(private val getUpcomingMovie: GetUpcomingMoviesAllUseCase) : ViewModel() {
    private val _upcomingMovies = MutableSharedFlow<List<UpcomingMovie>>(replay = 1)
    val upcomingMovies: Flow<List<UpcomingMovie>> = _upcomingMovies

    init {
        fetchUpcomingMovies()
    }
    private fun fetchUpcomingMovies() {
        getUpcomingMovie().onEach {
            _upcomingMovies.emit(it)
        }.launchIn(viewModelScope)
    }
}