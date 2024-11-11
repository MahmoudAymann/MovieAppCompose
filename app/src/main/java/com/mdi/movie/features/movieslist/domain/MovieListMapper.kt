package com.mdi.movie.features.movieslist.domain

import com.mdi.movie.BuildConfig
import com.mdi.movie.features.movieslist.data.model.MovieEntity
import com.mdi.movie.features.movieslist.data.model.MovieListResponseItem
import com.mdi.movie.features.movieslist.ui.model.MovieItem

object MovieListMapper {

    fun toMovieEntity(resItem: MovieListResponseItem): MovieEntity = MovieEntity(
        id = resItem.id ?: 0,
        name = resItem.title.orEmpty(),
        image = "${BuildConfig.IMAGE_BASE_URL}${resItem.posterPath}",
        releaseDate = resItem.releaseDate.orEmpty(),
        rating = resItem.voteAverage ?: 0.0
    )

    private fun toUiMovieItem(resItem: MovieEntity): MovieItem = MovieItem(
        id = resItem.id,
        name = resItem.name,
        image = resItem.image,
        releaseDate = resItem.releaseDate,
        rating = resItem.rating
    )

    fun listToUiListOfMovieItem(list: List<MovieEntity>): List<MovieItem> =
        list.map { toUiMovieItem(it) }

}