package com.mdi.movie.features.movies.movieslist.ui.model

import com.mdi.movie.core.utils.UIModel

@UIModel
data class MovieItem(
    val id: Int,
    val name: String,
    val image:String,
    val releaseDate: String,
    val rating: Double
)