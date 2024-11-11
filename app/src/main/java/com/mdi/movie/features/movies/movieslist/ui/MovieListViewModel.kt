package com.mdi.movie.features.movies.movieslist.ui

import androidx.lifecycle.viewModelScope
import com.mdi.movie.core.ui.viewmodel.BaseViewModel
import com.mdi.movie.features.movies.movieslist.domain.GetMoviesUseCase
import com.mdi.movie.features.movies.movieslist.domain.model.MovieParams
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : BaseViewModel<MovieListContract.Event, MovieListContract.State, MovieListContract.Effect>() {

    private var currentPage = 1
    private var isLastPage = false

    override fun setInitialState(): MovieListContract.State = MovieListContract.State(isLoading = true)

    override fun handleEvents(event: MovieListContract.Event) {
        when (event) {
            is MovieListContract.Event.GetMovies -> {
                resetMoviesList()
                val params = event.movieParams.copy(page = currentPage)
                getMovies(params)
            }

            is MovieListContract.Event.MovieSelected -> {
                setEffect { MovieListContract.Effect.NavigateToMovieDetails(event.movieId) }
            }

            is MovieListContract.Event.LoadMore -> {
                if (!isLastPage) {
                    getMovies(MovieParams(type = event.type, page = currentPage))
                }
            }
        }
    }

    private fun resetMoviesList() {
        currentPage = 1
        isLastPage = false
        setState { copy(moviesList = emptyList()) } // Clear the movie list
    }

    private fun getMovies(movieParams: MovieParams) {
        if (isLastPage) return
        setState { copy(isLoading = true, error = null) }
        viewModelScope.launch {
            getMoviesUseCase(movieParams).collect { result ->
                result.onSuccess { movies ->
                    val updatedMoviesList = if (movieParams.page > 1) {
                        viewState.value.moviesList + movies
                    } else {
                        movies
                    }
                    // Check if we reached the last page
                    //check for empty movies for simplicity
                    isLastPage = movies.isEmpty()
                    setState {
                        copy(
                            isLoading = false, moviesList = updatedMoviesList, error = null
                        )
                    }
                    currentPage++ // Increment the page for the next load
                }.onFailure { error ->
                    setState { copy(isLoading = false, error = error.message) }
                }
            }
        }
    }


}