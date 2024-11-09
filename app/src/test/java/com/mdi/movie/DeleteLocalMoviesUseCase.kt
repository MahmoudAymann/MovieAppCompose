package com.mdi.movie

import com.mdi.movie.features.movieslist.data.repo.MovieRepository
import com.mdi.movie.features.movieslist.domain.DeleteLocalMoviesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class DeleteLocalMoviesUseCaseTest {

    private val movieRepository: MovieRepository = Mockito.mock(MovieRepository::class.java)
    private val deleteLocalMoviesUseCase = DeleteLocalMoviesUseCase(movieRepository)

    @Test
    fun `invoke should call clearLocalMovies on repository`() = runBlockingTest {
        // Act
        deleteLocalMoviesUseCase()

        // Assert
        Mockito.verify(movieRepository).clearLocalMovies()
    }
}