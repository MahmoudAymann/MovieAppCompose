package com.mdi.movie.features.movieslist.data.repo

import com.mdi.movie.features.movieslist.data.model.MovieEntity
import com.mdi.movie.features.movieslist.data.model.MovieListResponseItem
import com.mdi.movie.features.movieslist.data.model.MoviesPagingResponse
import com.mdi.movie.features.movieslist.domain.model.MovieParams

interface MovieRepository {
    suspend fun getMoviesFromRemote(movieParams: MovieParams): MoviesPagingResponse
    suspend fun getMoviesFromLocal(): List<MovieEntity>
    suspend fun saveMoviesToLocal(movies: List<MovieEntity>)
    suspend fun clearLocalMovies()
    suspend fun getMovieDetailsFromLocal(movieId: Int): MovieEntity?
    suspend fun getMovieDetailsFromRemote(movieId: Int): MovieListResponseItem
}