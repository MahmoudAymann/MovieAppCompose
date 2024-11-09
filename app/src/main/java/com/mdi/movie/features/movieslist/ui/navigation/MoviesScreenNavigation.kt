package com.mdi.movie.features.movieslist.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mdi.movie.core.navigation.navigateToMovieDetails
import com.mdi.movie.features.main.MainViewModel
import com.mdi.movie.features.movieslist.ui.MoviesContract
import com.mdi.movie.features.movieslist.ui.MoviesListScreen
import com.mdi.movie.features.movieslist.ui.MoviesListViewModel

@Composable
fun MoviesScreenNavigation(
    navController: NavController, modifier: Modifier, mainViewModel: MainViewModel
) {
    val viewModel: MoviesListViewModel = hiltViewModel()
    MoviesListScreen(modifier = modifier,
        viewModel = viewModel,
        mainViewModel = mainViewModel,
        onNavigationRequest = { effect ->
            if (effect is MoviesContract.Effect.NavigateToMovieDetails) {
                navController.navigateToMovieDetails(effect.movieId)
            }
        })
}