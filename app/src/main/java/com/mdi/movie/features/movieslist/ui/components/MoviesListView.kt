package com.mdi.movie.features.movieslist.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.mdi.movie.features.movieslist.ui.model.MovieItem

@Composable
fun MoviesListView(
    movies: List<MovieItem>,
    onMovieItemClick: ((movieId: Int) -> Unit)?,
) {
    LazyColumn {
        items(movies) { movie ->
            MovieItemView(item = movie, onItemClick = { onMovieItemClick?.invoke(movie.id) })
        }
    }
}