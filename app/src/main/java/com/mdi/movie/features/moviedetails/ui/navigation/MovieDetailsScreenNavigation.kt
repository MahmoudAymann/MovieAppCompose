package com.mdi.movie.features.moviedetails.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.mdi.movie.features.moviedetails.ui.MovieDetailsScreen
import com.mdi.movie.features.moviedetails.ui.MovieDetailsViewModel

@Composable
fun MovieDetailsScreenNavigation(
    modifier: Modifier,
    movieId: Int,
) {
    val viewModel: MovieDetailsViewModel = hiltViewModel()
    MovieDetailsScreen(
        modifier = modifier, viewModel = viewModel, movieId = movieId
    )
}