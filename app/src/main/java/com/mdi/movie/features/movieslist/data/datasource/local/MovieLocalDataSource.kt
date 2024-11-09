package com.mdi.movie.features.movieslist.data.datasource.local

import com.mdi.movie.features.movieslist.data.model.MovieEntity

interface MovieLocalDataSource {
    suspend fun getAllMovies(): List<MovieEntity>
    suspend fun saveMovies(movies: List<MovieEntity>)
    suspend fun clearMovies()
}