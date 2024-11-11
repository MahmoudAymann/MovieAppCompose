package com.mdi.movie.features.movies.moviedetails.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.mdi.movie.R
import com.mdi.movie.core.ui.components.AppProgressBar
import com.mdi.movie.core.ui.components.ErrorDialog
import com.mdi.movie.features.movies.moviedetails.ui.model.MovieDetails

@Composable
fun MovieDetailsScreen(
    modifier: Modifier,
    viewModel: MovieDetailsViewModel,
    movieId: Int,
) {
    LaunchedEffect(Unit) {
        viewModel.setEvent(MovieDetailsContractor.Event.GetDetails(movieId))
    }
    Box(modifier = modifier) {
        val state = viewModel.viewState.value
        when {
            state.isLoading && state.movieDetails == null -> {
                AppProgressBar()
            }
            state.movieDetails != null && !state.isLoading -> {
                MovieDetailsContent(state.movieDetails)
            }
            !state.error.isNullOrBlank() && !state.isLoading -> {
                ErrorDialog(message = state.error)
            }
        }
    }
}

@Composable
fun MovieDetailsContent(item: MovieDetails) {

    // Outer scrollable column
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Movie Image
        Image(
            painter = rememberAsyncImagePainter(model = item.image),
            contentDescription = stringResource(id = R.string.movie_poster),
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Movie Title
        Text(
            text = item.name,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Rating and Release Date Row
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            Text(
                text = item.rating.toString(),
                style = MaterialTheme.typography.bodySmall,
                color = Color.Yellow
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = item.releaseDate,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Genres Row
        LazyRow(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(item.genres) { genre ->
                Text(
                    text = genre,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.secondary,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(horizontal = 12.dp, vertical = 4.dp),
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Overview
        Text(
            text = stringResource(R.string.overview),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = item.overview, style = MaterialTheme.typography.bodySmall, color = Color.Gray
        )
    }
}
