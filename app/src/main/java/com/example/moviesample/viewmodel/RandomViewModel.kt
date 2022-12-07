package com.example.moviesample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesample.api.model.UpcomingMovie
import com.example.moviesample.usecase.GetUpcomingMovieUseCase
import com.example.moviesample.usecase.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class RandomViewModel(private val getUpcomingMovie: GetUpcomingMovieUseCase) : ViewModel() {
    val currentMovie = MutableStateFlow<UpcomingMovie?>(null)
    val networkState = MutableStateFlow<NetworkState>(NetworkState.Loading)
    init {
        getRandomMovie()
    }

    fun onClick() {
        getRandomMovie()
    }

    private fun getRandomMovie() {
        getUpcomingMovie().onEach { state ->
            when (state) {
                State.Error -> networkState.emit(NetworkState.Error)
                State.Loading -> networkState.emit(NetworkState.Loading)
                is State.Success -> {
                    networkState.emit(NetworkState.Success)
                    currentMovie.emit(state.movie)
                }
            }
        }.launchIn(viewModelScope)
    }

    sealed class NetworkState {
        object Loading : NetworkState()
        object Success : NetworkState()
        object Error : NetworkState()
    }
}