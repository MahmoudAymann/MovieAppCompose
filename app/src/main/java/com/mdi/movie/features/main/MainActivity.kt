package com.mdi.movie.features.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mdi.movie.R
import com.mdi.movie.core.navigation.AppNavigation.Routes.MOVIES_LIST_SCREEN
import com.mdi.movie.core.navigation.AppNavigation.Routes.MOVIE_DETAILS_SCREEN
import com.mdi.movie.core.navigation.MovieAppNavGraph
import com.mdi.movie.core.ui.components.AppActionBar
import com.mdi.movie.core.ui.theme.MovieAppTheme
import com.mdi.movie.features.movies.movieslist.data.model.MoviesType

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieAppTheme {
                val mainViewModel: MainViewModel = hiltViewModel()
                MainContent(mainViewModel = mainViewModel)
            }
        }
    }
}

@Composable
fun MainContent(mainViewModel: MainViewModel) {

    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(topBar = { GetMyTopBar(currentBackStackEntry, mainViewModel, navController) }) { padding ->
        MovieAppNavGraph(
            modifier = Modifier
                .padding(padding)
                .then(Modifier.padding(8.dp)),
            mainViewModel = mainViewModel,
            navController = navController
        )
    }
}

@Composable
private fun GetMyTopBar(
    currentBackStackEntry: NavBackStackEntry?,
    mainViewModel: MainViewModel,
    navController: NavHostController
) {
    var selectedTypeState by remember { mutableStateOf(MoviesType.Popular) }
    var isDropdownExpanded by remember { mutableStateOf(false) }

    // Determine if the back button should be shown based on the current route
    val showBackButton = getBackButtonScreen(currentBackStackEntry)
    val showDropDownMenu = getDropDownMenuScreen(currentBackStackEntry)

    AppActionBar(stringResource(id = R.string.app_name),
        onTypeSelected = { selectedType ->
        selectedTypeState = selectedType
        //send the action to movies list screen
        mainViewModel.updateSelectedType(selectedType)
    }, isDropdownExpanded = isDropdownExpanded,
        onDropdownToggle = { isDropdownExpanded = !isDropdownExpanded },
        onBackIconClick = if (showBackButton) { { navController.popBackStack() }
    } else null,
        showDropDownMenu = showDropDownMenu)

}

private fun getDropDownMenuScreen(currentBackStackEntry: NavBackStackEntry?): Boolean {
    return currentBackStackEntry?.destination?.route == MOVIES_LIST_SCREEN
}

private fun getBackButtonScreen(currentBackStackEntry: NavBackStackEntry?): Boolean {
    return currentBackStackEntry?.destination?.route == MOVIE_DETAILS_SCREEN
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    MovieAppTheme {
        MainContent(hiltViewModel())
    }
}