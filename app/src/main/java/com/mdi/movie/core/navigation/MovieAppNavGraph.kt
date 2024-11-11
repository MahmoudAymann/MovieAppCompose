package com.mdi.movie.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mdi.movie.core.navigation.AppNavigation.Args.MOVIE_ID
import com.mdi.movie.core.navigation.AppNavigation.Routes.MOVIES_LIST_SCREEN
import com.mdi.movie.core.navigation.AppNavigation.Routes.MOVIE_DETAILS_SCREEN
import com.mdi.movie.features.main.MainViewModel
import com.mdi.movie.features.moviedetails.ui.navigation.MovieDetailsScreenNavigation
import com.mdi.movie.features.movieslist.ui.navigation.MoviesScreenNavigation

@Composable
fun MovieAppNavGraph(
    modifier: Modifier = Modifier, navController: NavHostController, mainViewModel: MainViewModel
) {
    NavHost(
        navController = navController, startDestination = MOVIES_LIST_SCREEN
    ) {
        composable(MOVIES_LIST_SCREEN) {
            MoviesScreenNavigation(
                navController = navController, modifier = modifier, mainViewModel = mainViewModel
            )
        }

        composable(route = MOVIE_DETAILS_SCREEN, arguments = listOf(navArgument(name = MOVIE_ID) {
            type = NavType.IntType
        })) { backStackEntry ->
            val movieId =
                requireNotNull(backStackEntry.arguments?.getInt(MOVIE_ID)) { "Movie id is required as an argument" }
            MovieDetailsScreenNavigation(modifier = modifier, movieId = movieId)
        }
    }
}

object AppNavigation {
    object Args {
        const val MOVIE_ID = "movie_id"
    }

    object Routes {
        const val MOVIES_LIST_SCREEN = "movies_list_screen"
        const val MOVIE_DETAILS_SCREEN = "movie_details_screen/{$MOVIE_ID}"
    }
}

fun NavController.navigateToMovieDetails(movieId: Int) = navigate(
    route = MOVIE_DETAILS_SCREEN.replace(
        "{$MOVIE_ID}", movieId.toString()
    )
)



