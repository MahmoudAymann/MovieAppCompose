package com.mdi.movie.features.movieslist.domain

import com.mdi.movie.core.BaseUseCase
import com.mdi.movie.features.movieslist.data.repo.MovieRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class DeleteLocalMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) : BaseUseCase<Unit> {
    override suspend fun invoke() {
        movieRepository.clearLocalMovies()
    }
}