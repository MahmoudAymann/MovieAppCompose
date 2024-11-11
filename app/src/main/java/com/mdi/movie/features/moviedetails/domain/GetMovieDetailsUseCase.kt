package com.mdi.movie.features.moviedetails.domain

import com.mdi.movie.core.BaseUseCaseParam
import com.mdi.movie.features.moviedetails.ui.model.MovieDetails
import com.mdi.movie.features.movieslist.data.repo.MovieRepository
import com.mdi.movie.features.movieslist.domain.MovieListMapper
import com.mdi.movie.features.movieslist.domain.MovieListMapper.toMovieDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseUseCaseParam<Int, Flow<Result<MovieDetails>>> {

    override suspend fun invoke(params: Int): Flow<Result<MovieDetails>> = flow {

        //get the movie from local if exist
        val localMovie = movieRepository.getMovieDetailsFromLocal(params)
        if (localMovie != null) emit(Result.success(localMovie.toMovieDetails()))

        try {
            // Fetch from remote if local data is empty
            val response = movieRepository.getMovieDetailsFromRemote(params)
            //Map the response
            val movieDetails = MovieListMapper.toMovieEntity(response).toMovieDetails()
            emit(Result.success(movieDetails))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }

    }

}