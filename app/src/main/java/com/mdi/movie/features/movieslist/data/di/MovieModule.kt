package com.mdi.movie.features.movieslist.data.di

import com.mdi.movie.core.localdb.room.AppDatabase
import com.mdi.movie.core.localdb.room.dao.MovieDao
import com.mdi.movie.core.network.ApiServices
import com.mdi.movie.features.movieslist.data.datasource.local.MovieLocalDataSource
import com.mdi.movie.features.movieslist.data.datasource.local.MovieLocalDataSourceImpl
import com.mdi.movie.features.movieslist.data.datasource.remote.MovieRemoteDataSource
import com.mdi.movie.features.movieslist.data.datasource.remote.MovieRemoteDataSourceImpl
import com.mdi.movie.features.movieslist.data.repo.MovieRepository
import com.mdi.movie.features.movieslist.data.repo.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class MovieModule {
    @Provides
    @Singleton
    fun provideMovieRemoteDataSource(
        apiServices: ApiServices
    ): MovieRemoteDataSource {
        return MovieRemoteDataSourceImpl(apiServices)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(
        remoteDataSource: MovieRemoteDataSource,
        localDataSource: MovieLocalDataSource
    ): MovieRepository {
        return MovieRepositoryImpl(localDataSource, remoteDataSource)
    }


    @Provides
    fun provideMovieDao(database: AppDatabase): MovieDao {
        return database.movieDao()
    }

    @Provides
    @Singleton
    fun provideMovieLocalDataSource(movieDao: MovieDao): MovieLocalDataSource {
        return MovieLocalDataSourceImpl(movieDao)
    }
}