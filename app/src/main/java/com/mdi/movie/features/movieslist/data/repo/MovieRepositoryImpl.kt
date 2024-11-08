package com.mdi.movie.features.movieslist.data.repo

import com.mdi.movie.features.movieslist.data.datasource.local.MovieLocalDataSource
import com.mdi.movie.features.movieslist.data.datasource.remote.MovieRemoteDataSource
import com.mdi.movie.features.movieslist.data.model.MovieEntity
import com.mdi.movie.features.movieslist.data.model.MoviesPagingResponse
import com.mdi.movie.features.movieslist.data.model.MoviesType
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val localDataSource: MovieLocalDataSource,
    private val remoteDataSource: MovieRemoteDataSource
) : MovieRepository {
    override suspend fun getMovies(
        type: MoviesType, page: Int
    ): MoviesPagingResponse = remoteDataSource.getMovies(type = type, page = page)

    override suspend fun getMoviesFromLocal(): List<MovieEntity> = localDataSource.getAllMovies()

    override suspend fun saveMoviesToLocal(movies: List<MovieEntity>) =
        localDataSource.saveMovies(movies)

    override suspend fun clearLocalMovies() = localDataSource.clearMovies()

}