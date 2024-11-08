package com.mdi.movie.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mdi.movie.features.main.MainViewModel
import com.mdi.movie.features.movieslist.ui.MoviesListScreen

@Composable
fun MovieAppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    mainViewModel: MainViewModel
) {
    NavHost(
        navController = navController,
        startDestination = NavigationScreenName.MOVIES_LIST_SCREEN
    ) {
        composable(NavigationScreenName.MOVIES_LIST_SCREEN) {
            MoviesListScreen(
                modifier = modifier,
                mainViewModel = mainViewModel,
                onMovieItemClick = { movieId ->

                }
            )
        }
    }
}