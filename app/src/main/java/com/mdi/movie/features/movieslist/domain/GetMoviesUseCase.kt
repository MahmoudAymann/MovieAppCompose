package com.mdi.movie.features.movieslist.domain

import com.mdi.movie.core.BaseUseCaseParam
import com.mdi.movie.core.network.BaseResult
import com.mdi.movie.features.movieslist.data.repo.MovieRepository
import com.mdi.movie.features.movieslist.domain.model.MovieParams
import com.mdi.movie.features.movieslist.ui.model.MovieItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetMoviesUseCase(
    private val movieRepository: MovieRepository
) : BaseUseCaseParam<MovieParams, Flow<BaseResult<List<MovieItem>>>> {

    override suspend operator fun invoke(params: MovieParams): Flow<BaseResult<List<MovieItem>>> =
        flow {
            emit(BaseResult.Loading)

            // Attempt to retrieve movies from the local cache first
            val localMovies = movieRepository.getMoviesFromLocal()
            if (localMovies.isNotEmpty()) {
                emit(BaseResult.Success(MovieListMapper.listToUiListOfMovieItem(localMovies)))
            } else {
                try {
                    // Fetch from remote if local data is empty
                    val response = movieRepository.getMoviesFromRemote(params)

                    // Map response to MovieEntity and store in local cache
                    val movies = response.moviesList?.filterNotNull()?.map { movie ->
                        MovieListMapper.toMovieEntity(movie)
                    } ?: emptyList()

                    // Cache the fetched movies
                    movieRepository.clearLocalMovies()
                    movieRepository.saveMoviesToLocal(movies)

                    // Emit the movies as a success result to UI after caching
                    emit(BaseResult.Success(MovieListMapper.listToUiListOfMovieItem(movies)))
                } catch (e: Exception) {
                    emit(BaseResult.Error(e))
                }
            }
        }
}