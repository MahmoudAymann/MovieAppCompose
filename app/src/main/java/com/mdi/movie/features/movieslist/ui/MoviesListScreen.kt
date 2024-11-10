package com.mdi.movie.features.movieslist.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.mdi.movie.core.ui.components.AppProgressBar
import com.mdi.movie.core.ui.components.ErrorDialog
import com.mdi.movie.features.main.MainViewModel
import com.mdi.movie.features.movieslist.domain.model.MovieParams
import com.mdi.movie.features.movieslist.ui.components.MoviesListView
import com.mdi.movie.features.movieslist.ui.model.MovieItem
import kotlinx.coroutines.flow.onEach

@Composable
fun MoviesListScreen(
    modifier: Modifier,
    viewModel: MoviesListViewModel,
    mainViewModel: MainViewModel,
    onNavigationRequest: (MoviesContract.Effect) -> Unit
) {
    val rememberListState = rememberLazyListState()
    val selectedType by mainViewModel.selectedTypeState.collectAsState()

    //Listen for changes of movie type from action bar
    LaunchedEffect(selectedType) {
        viewModel.setEvent(MoviesContract.Event.GetMovies(MovieParams(type = selectedType)))
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
        MoviesScreenContent(viewModel.viewState.value, onLoadNextPage = {
            viewModel.setEvent(MoviesContract.Event.LoadMore(mainViewModel.selectedTypeState.value))
        }, onMovieItemClick = {
            viewModel.setEvent(MoviesContract.Event.MovieSelected(it.id))
        }, listState = rememberListState)
    }
}

@Composable
fun MoviesScreenContent(
    state: MoviesContract.State,
    onMovieItemClick: (MovieItem) -> Unit,
    onLoadNextPage: () -> Unit, // Trigger to load the next page
    listState: LazyListState
) {
    when {
        state.isLoading && state.moviesList.isEmpty() -> AppProgressBar()
        state.error.isNullOrBlank().not() -> ErrorDialog(message = state.error!!)
        state.moviesList.isNotEmpty() -> MoviesListView(
            movies = state.moviesList,
            onMovieItemClick = onMovieItemClick,
            onLoadNextPage = onLoadNextPage,
            isLoadingMore = state.isLoading,
            listState = listState
        )
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