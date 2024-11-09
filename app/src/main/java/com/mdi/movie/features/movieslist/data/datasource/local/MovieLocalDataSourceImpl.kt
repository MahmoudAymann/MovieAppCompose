package com.mdi.movie.features.movieslist.data.datasource.local

import com.mdi.movie.core.localdb.room.dao.MovieDao
import com.mdi.movie.features.movieslist.data.model.MovieEntity

class MovieLocalDataSourceImpl(
    private val movieDao: MovieDao
) : MovieLocalDataSource {

    override suspend fun getAllMovies(): List<MovieEntity> {
        return movieDao.getAllMovies()
    }

    override suspend fun saveMovies(movies: List<MovieEntity>) {
        movieDao.insertMovies(movies)
    }

    override suspend fun clearMovies() {
        movieDao.clearAllMovies()
    }
}