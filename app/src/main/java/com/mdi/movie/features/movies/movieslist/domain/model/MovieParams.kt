package com.mdi.movie.features.movies.movieslist.domain.model

import com.mdi.movie.features.movies.movieslist.data.model.MoviesType

/*
* Request body params
* */
data class MovieParams(val type: MoviesType, val page: Int = 1)
