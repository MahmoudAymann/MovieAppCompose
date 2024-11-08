package com.mdi.movie.features.movieslist.domain.model

import com.mdi.movie.features.movieslist.data.model.MoviesType

/*
* Request body params
* */
data class MovieParams(val type: MoviesType, val page: Int = 1)
