package com.mdi.movie.features.movieslist.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.mdi.movie.features.movieslist.ui.model.MovieItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor() : ViewModel() {

    private val _movies = mutableStateListOf<MovieItem>()
    val movies: List<MovieItem> get() = _movies


    init {
        // Initialize with a paginated list or any static list for testing
        _movies.addAll(
            List(100) { index ->
                MovieItem(
                    id = index,
                    name = "Movie $index",
                    releaseDate = "2022-01-01",
                    rating = (5..10).random().toDouble()
                )
            }
        )
    }

    // Toggle favorite status for a specific movie by ID
    fun toggleFavorite(movieId: Int) {
        val index = _movies.indexOfFirst { it.id == movieId }
        if (index >= 0) {
            val movie = _movies[index]
            // Update only the specific movie in the list
            _movies[index] = movie.copy(isFavorite = !movie.isFavorite)
        }
    }

}