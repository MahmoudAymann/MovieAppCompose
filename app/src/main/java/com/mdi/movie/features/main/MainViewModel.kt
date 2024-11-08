package com.mdi.movie.features.main

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import com.mdi.movie.features.movieslist.data.model.MoviesType

class MainViewModel : ViewModel() {
    private val _selectedTypeState = mutableStateOf(MoviesType.Popular)
    val selectedTypeState: State<MoviesType> = _selectedTypeState

    fun updateSelectedType(type: MoviesType) {
        _selectedTypeState.value = type
    }
}