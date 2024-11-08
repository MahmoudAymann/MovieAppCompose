package com.mdi.movie.features.movieslist.data.datasource.remote

import com.mdi.movie.features.movieslist.data.model.MoviesPagingResponse
import com.mdi.movie.features.movieslist.data.model.MoviesType

interface MovieRemoteDataSource {
    suspend fun getMovies(type: MoviesType, page: Int): MoviesPagingResponse
}