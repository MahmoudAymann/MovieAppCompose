package com.mdi.movie.features.movieslist.ui.model

import com.mdi.movie.core.utils.UIModel

@UIModel
data class MovieItem(
    val id: Int,
    val name: String,
    val releaseDate: String,
    val rating: Double,
    val isFavorite: Boolean = false
)