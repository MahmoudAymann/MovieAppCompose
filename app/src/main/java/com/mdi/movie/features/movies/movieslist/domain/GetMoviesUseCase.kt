package com.mdi.movie.features.movies.movieslist.domain

import com.mdi.movie.core.BaseUseCaseParam
import com.mdi.movie.features.movies.data.repo.MovieRepository
import com.mdi.movie.features.movies.domain.MovieListMapper
import com.mdi.movie.features.movies.movieslist.domain.model.MovieParams
import com.mdi.movie.features.movies.movieslist.ui.model.MovieItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseUseCaseParam<MovieParams, Flow<Result<List<MovieItem>>>> {

    override suspend operator fun invoke(params: MovieParams): Flow<Result<List<MovieItem>>> =
        flow {
            // Attempt to retrieve movies from the local cache first
            val localMovies = movieRepository.getMoviesFromLocal()
            if (localMovies.isNotEmpty()) {
                emit(Result.success(MovieListMapper.listToUiListOfMovieItem(localMovies)))
            }
            //Do fetch from remote
            try {
                // Fetch from remote if local data is empty
                val response = movieRepository.getMoviesFromRemote(params)

                // Map response to MovieEntity and store in local cache
                val movies = response.moviesList?.filterNotNull()?.map { movie ->
                    MovieListMapper.toMovieEntity(movie)
                } ?: emptyList()

                // Cache the fetched movies
                movieRepository.saveMoviesToLocal(movies)

                // Emit the movies as a success result to UI after caching
                emit(Result.success(MovieListMapper.listToUiListOfMovieItem(movies)))
            } catch (e: Exception) {
                emit(Result.failure(e))
            }

        }
}