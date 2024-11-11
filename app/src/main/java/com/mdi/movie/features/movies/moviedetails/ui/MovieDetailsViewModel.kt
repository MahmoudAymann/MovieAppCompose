package com.mdi.movie.features.movies.moviedetails.ui

import androidx.lifecycle.viewModelScope
import com.mdi.movie.core.ui.viewmodel.BaseViewModel
import com.mdi.movie.features.movies.moviedetails.domain.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : BaseViewModel<MovieDetailsContractor.Event, MovieDetailsContractor.State, MovieDetailsContractor.Effect>() {

    override fun setInitialState(): MovieDetailsContractor.State = MovieDetailsContractor.State()

    override fun handleEvents(event: MovieDetailsContractor.Event) {
        when (event) {
            is MovieDetailsContractor.Event.GetDetails -> {
                getMovieDetails(event.movieId)
            }
        }
    }


    private fun getMovieDetails(movieId: Int) {
        setState { copy(isLoading = true, error = null) }
        viewModelScope.launch {
            getMovieDetailsUseCase(movieId).collect{
                it.onSuccess {
                    setState { copy(isLoading = false, movieDetails = it) }
                }.onFailure {
                    setState { copy(isLoading = false, error = it.message) }
                }
            }
        }
    }

}