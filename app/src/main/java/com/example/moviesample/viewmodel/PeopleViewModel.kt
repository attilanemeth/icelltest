package com.example.moviesample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesample.api.model.Person
import com.example.moviesample.usecase.GetPopularPeopleUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class PeopleViewModel(private val getPopularPeopleUseCase: GetPopularPeopleUseCase) : ViewModel() {
    val peoples = MutableStateFlow<List<Person>>(emptyList())
    private val peopleFLow = getPopularPeopleUseCase()

    init {
        peopleFLow.onEach {
            peoples.emit(it)
        }.launchIn(viewModelScope)
    }
}