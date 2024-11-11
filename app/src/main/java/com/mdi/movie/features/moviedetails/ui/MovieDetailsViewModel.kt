package com.mdi.movie.features.moviedetails.ui

import androidx.lifecycle.viewModelScope
import com.mdi.movie.core.ui.viewmodel.BaseViewModel
import com.mdi.movie.features.moviedetails.ui.model.MovieDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieDetailsViewModel @Inject constructor() :
    BaseViewModel<MovieDetailsContractor.Event, MovieDetailsContractor.State, MovieDetailsContractor.Effect>() {

    override fun setInitialState(): MovieDetailsContractor.State = MovieDetailsContractor.State(
        isLoading = false, error = null
    )

    override fun handleEvents(event: MovieDetailsContractor.Event) {
        when (event) {
            is MovieDetailsContractor.Event.GetDetails -> {
                getMovieDetails()
            }
        }
    }


    private fun getMovieDetails() {
        setState { copy(isLoading = true, error = null) }
        viewModelScope.launch {
            delay(1000)
            setState {
                copy(
                    isLoading = false, movieDetails = MovieDetails(
                        id = 9185,
                        name = "Caitlin McBride",
                        image = "mus",
                        releaseDate = "menandri",
                        rating = 12.13,
                        overview = "tale",
                        genres = listOf("hi")
                    )
                )
            }
        }
    }

}