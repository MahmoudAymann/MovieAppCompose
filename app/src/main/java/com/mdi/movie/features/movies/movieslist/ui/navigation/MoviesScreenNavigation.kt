package com.mdi.movie.features.movies.movieslist.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mdi.movie.core.navigation.navigateToMovieDetails
import com.mdi.movie.features.main.MainViewModel
import com.mdi.movie.features.movies.movieslist.ui.MovieListContract
import com.mdi.movie.features.movies.movieslist.ui.MoviesListScreen
import com.mdi.movie.features.movies.movieslist.ui.MovieListViewModel

@Composable
fun MoviesScreenNavigation(
    navController: NavController, modifier: Modifier, mainViewModel: MainViewModel
) {
    val viewModel: MovieListViewModel = hiltViewModel()
    MoviesListScreen(modifier = modifier,
        viewModel = viewModel,
        mainViewModel = mainViewModel,
        onNavigationRequest = { effect ->
            if (effect is MovieListContract.Effect.NavigateToMovieDetails) {
                navController.navigateToMovieDetails(effect.movieId)
            }
        })
}