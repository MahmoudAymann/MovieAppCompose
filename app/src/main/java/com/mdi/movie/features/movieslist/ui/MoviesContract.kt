package com.mdi.movie.features.movieslist.ui

import com.mdi.movie.core.ui.viewmodel.ViewEvent
import com.mdi.movie.core.ui.viewmodel.ViewSideEffect
import com.mdi.movie.core.ui.viewmodel.ViewState
import com.mdi.movie.features.movieslist.domain.model.MovieParams
import com.mdi.movie.features.movieslist.ui.model.MovieItem

class MoviesContract {

    sealed class Event : ViewEvent {
        /* reset list and start over */
        data class ResetAndGetMovies(val movieParams: MovieParams) : Event()
        data class GetMovies(val movieParams: MovieParams) : Event() //for pagination
        data class MovieSelected(val movieId: Int) : Event()
    }

    data class State(
        val isLoading: Boolean = false,
        val moviesList: List<MovieItem> = emptyList(),
        val error: String? = null
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        data class NavigateToMovieDetails(val movieId: Int) : Effect()
    }

}