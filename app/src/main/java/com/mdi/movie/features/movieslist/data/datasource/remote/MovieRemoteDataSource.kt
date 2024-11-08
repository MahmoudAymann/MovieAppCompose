package com.mdi.movie.features.movieslist.data.datasource.remote

import com.mdi.movie.features.movieslist.data.model.MoviesPagingResponse
import com.mdi.movie.features.movieslist.data.model.MoviesType
import com.mdi.movie.features.movieslist.domain.model.MovieParams

interface MovieRemoteDataSource {
    suspend fun getMovies(movieParams: MovieParams): MoviesPagingResponse
}