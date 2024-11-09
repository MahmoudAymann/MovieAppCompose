package com.mdi.movie.features.movieslist.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.mdi.movie.core.ui.components.AppProgressBar
import com.mdi.movie.core.ui.components.ErrorDialog
import com.mdi.movie.features.main.MainViewModel
import com.mdi.movie.features.movieslist.domain.model.MovieParams
import com.mdi.movie.features.movieslist.ui.components.MoviesListView
import kotlinx.coroutines.flow.onEach

@Composable
fun MoviesListScreen(
    modifier: Modifier,
    viewModel: MoviesListViewModel,
    mainViewModel: MainViewModel,
    onNavigationRequest: (MoviesContract.Effect) -> Unit
) {

    val selectedType = mainViewModel.selectedTypeState.collectAsState()
    //Listen for changes of movie type from action bar
    LaunchedEffect(selectedType) {
        viewModel.setEvent(MoviesContract.Event.ResetAndGetMovies(MovieParams(type = selectedType.value)))
    }

    //listen for effects from viewModel
    LaunchedEffect(Unit) {
        viewModel.effect.onEach { effect ->
            when (effect) {
                is MoviesContract.Effect.NavigateToMovieDetails -> onNavigationRequest.invoke(effect)
            }
        }
    }
    //Screen Layout
    Box(modifier = modifier) {
        MoviesScreenContent(viewModel.viewState.value, onMovieItemClick = {
            viewModel.setEvent(MoviesContract.Event.MovieSelected(it))
        })
    }
}

@Composable
fun MoviesScreenContent(
    state: MoviesContract.State,
    onMovieItemClick: (Int) -> Unit,
) {
    when {
        state.isLoading -> AppProgressBar()
        state.error.isNullOrBlank().not() -> ErrorDialog(message = state.error!!)
        state.moviesList.isNotEmpty() -> MoviesListView(state.moviesList, onMovieItemClick)
    }
}


@Preview(showSystemUi = true)
@Composable
fun PreviewMoviesListScreen() {
    MoviesListScreen(modifier = Modifier,
        mainViewModel = hiltViewModel(),
        viewModel = hiltViewModel(),
        onNavigationRequest = {})
}