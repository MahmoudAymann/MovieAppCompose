package com.mdi.movie.features.main

import androidx.lifecycle.ViewModel
import com.mdi.movie.features.movies.movieslist.data.model.MoviesType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
    private val _selectedTypeState = MutableStateFlow(MoviesType.Popular)
    val selectedTypeState: StateFlow<MoviesType> = _selectedTypeState.asStateFlow()

    fun updateSelectedType(type: MoviesType) {
        _selectedTypeState.value = type
    }
}