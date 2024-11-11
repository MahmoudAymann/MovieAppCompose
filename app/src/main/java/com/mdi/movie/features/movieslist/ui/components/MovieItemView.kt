package com.mdi.movie.features.movieslist.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.mdi.movie.R
import com.mdi.movie.features.movieslist.ui.model.MovieItem


@Composable
fun MovieItemView(
    item: MovieItem,
    onItemClick: (MovieItem) -> Unit,
) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clickable {
            onItemClick.invoke(item)
        }) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Image
            Image(
                painter = rememberAsyncImagePainter(model = item.image),
                contentDescription = stringResource(R.string.movie_poster),
                modifier = Modifier
                    .size(80.dp)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(text = item.name, style = MaterialTheme.typography.titleMedium)
            Text(
                text = stringResource(R.string.release_date, item.releaseDate),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = stringResource(R.string.rating, item.rating),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewMovieItemView() {
    MovieItemView(
        onItemClick = {}, item = MovieItem(
            id = 7931,
            name = stringResource(R.string.app_name),
            image = stringResource(R.string.app_name),
            releaseDate = stringResource(R.string.preview_date),
            rating = 2.0
        )
    )
}