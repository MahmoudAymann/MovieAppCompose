package com.mdi.movie.features.movies.moviedetails.ui.model

import com.mdi.movie.core.utils.UIModel

@UIModel
data class MovieDetails(
    val id: Int,
    val name: String,
    val image: String,
    val releaseDate: String,
    val rating: Double,
    val overview: String,
    val genres: List<String>
)
