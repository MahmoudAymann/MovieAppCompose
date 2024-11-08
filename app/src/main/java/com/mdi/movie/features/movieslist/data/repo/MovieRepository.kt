package com.mdi.movie.features.movieslist.data.repo

import com.mdi.movie.features.movieslist.data.model.MovieEntity
import com.mdi.movie.features.movieslist.data.model.MoviesPagingResponse
import com.mdi.movie.features.movieslist.data.model.MoviesType

interface MovieRepository {
    suspend fun getMovies(type: MoviesType, page: Int): MoviesPagingResponse
    suspend fun getMoviesFromLocal(): List<MovieEntity>
    suspend fun saveMoviesToLocal(movies: List<MovieEntity>)
    suspend fun clearLocalMovies()
}