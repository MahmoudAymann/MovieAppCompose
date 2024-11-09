package com.mdi.movie.features.movieslist.ui

import androidx.lifecycle.viewModelScope
import com.mdi.movie.core.ui.viewmodel.BaseViewModel
import com.mdi.movie.features.movieslist.domain.GetMoviesUseCase
import com.mdi.movie.features.movieslist.domain.model.MovieParams
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : BaseViewModel<MoviesContract.Event, MoviesContract.State, MoviesContract.Effect>() {

    private suspend fun fetchMovies(movieParams: MovieParams) {
        getMoviesUseCase(movieParams).collect { result ->

        }
    }

    override fun setInitialState(): MoviesContract.State = MoviesContract.State(isLoading = true)

    override fun handleEvents(event: MoviesContract.Event) {
        when (event) {
            is MoviesContract.Event.GetMovies -> {
                getMovies(event.movieParams)
            }

            is MoviesContract.Event.MovieSelected -> {
                setEffect { MoviesContract.Effect.NavigateToMovieDetails(event.movieId) }
            }
            is MoviesContract.Event.ResetAndGetMovies -> {
                val params = event.movieParams.copy(page = 1)
                getMovies(params)
            }
        }
    }

    private fun getMovies(movieParams: MovieParams) {
        setState { copy(isLoading = true, error = null) }
        viewModelScope.launch {
            getMoviesUseCase(movieParams).collect {
                it.onSuccess {

                }.onFailure {

                }
            }
        }
    }


}