package com.mdi.movie.features.movies.moviedetails.ui

import com.mdi.movie.core.ui.viewmodel.ViewEvent
import com.mdi.movie.core.ui.viewmodel.ViewSideEffect
import com.mdi.movie.core.ui.viewmodel.ViewState
import com.mdi.movie.features.movies.moviedetails.ui.model.MovieDetails

class MovieDetailsContractor {

    sealed class Event : ViewEvent {
        data class GetDetails(val movieId: Int) : Event()
    }

    data class State(
        val isLoading: Boolean = false,
        val movieDetails: MovieDetails? = null,
        val error: String? = null
    ) : ViewState

    sealed class Effect : ViewSideEffect

}