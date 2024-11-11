package com.mdi.movie.features.movies.data.datasource.local

import com.mdi.movie.features.movies.data.model.MovieEntity


interface MovieLocalDataSource {
    suspend fun getAllMovies(): List<MovieEntity>
    suspend fun getMovieById(movieId: Int): MovieEntity?
    suspend fun saveMovies(movies: List<MovieEntity>)
    suspend fun clearMovies()
}