package com.mdi.movie.features.movieslist.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.mdi.movie.features.movieslist.ui.components.MovieItemView
import com.mdi.movie.features.movieslist.ui.model.MovieItem

@Composable
fun MoviesListScreen(
    modifier: Modifier,
    viewModel: MoviesListViewModel = hiltViewModel(),
    onMovieItemClick: ((movieId: Int) -> Unit)? = null
) {
    val movies = viewModel.movies
    Box(modifier = modifier) {
        MoviesListView(movies, onMovieItemClick, onFavouriteClick = { movieId ->
            viewModel.toggleFavorite(movieId)
        })
    }
}

@Composable
fun MoviesListView(
    movies: List<MovieItem>,
    onMovieItemClick: ((movieId: Int) -> Unit)?,
    onFavouriteClick: (movieId: Int) -> Unit
) {
    LazyColumn {
        items(movies) { movie ->
            MovieItemView(item = movie,
                onItemClick = { onMovieItemClick?.invoke(movie.id) },
                onFavouriteClick = { onFavouriteClick.invoke(movie.id) })
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun PreviewMoviesListScreen() {
    MoviesListScreen(modifier = Modifier, onMovieItemClick = {})
}