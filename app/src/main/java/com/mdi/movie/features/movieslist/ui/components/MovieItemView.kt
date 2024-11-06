package com.mdi.movie.features.movieslist.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mdi.movie.R
import com.mdi.movie.features.movieslist.ui.model.MovieItem


@Composable
fun MovieItemView(
    item: MovieItem,
    onItemClick: (MovieItem) -> Unit,
    onFavouriteClick: (id: Int) -> Unit
) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clickable {
            onItemClick.invoke(item)
        }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = item.name, style = MaterialTheme.typography.titleMedium)
                Text(text = stringResource(R.string.release_date, item.releaseDate), style = MaterialTheme.typography.bodySmall)
                Text(text = stringResource(R.string.rating, item.rating), style = MaterialTheme.typography.bodySmall)
            }
            IconButton(
                onClick = { onFavouriteClick(item.id) } // Trigger favorite toggle
            ) {
                Icon(
                    imageVector = if (item.isFavorite) Icons.Filled.Favorite else Icons.Outlined.Favorite,
                    contentDescription = if (item.isFavorite) "Unfavorite" else "Favorite",
                    tint = if (item.isFavorite) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewMovieItemView() {
    MovieItemView(
        onFavouriteClick = {},
        onItemClick = {},
        item = MovieItem(
            isFavorite = true,
            id = 7931, name = "Milagros Klein",
            releaseDate = "12/2/2020",
            rating = 2.0
        )
    )
}