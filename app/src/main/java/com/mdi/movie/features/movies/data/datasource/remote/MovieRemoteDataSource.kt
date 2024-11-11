package com.mdi.movie.features.movies.data.datasource.remote

import com.mdi.movie.features.movies.movieslist.data.model.MovieListResponseItem
import com.mdi.movie.features.movies.movieslist.data.model.MoviesPagingResponse
import com.mdi.movie.features.movies.movieslist.domain.model.MovieParams


interface MovieRemoteDataSource {
    suspend fun getMovies(movieParams: MovieParams): MoviesPagingResponse
    suspend fun getMovieDetails(movieId: Int): MovieListResponseItem
}