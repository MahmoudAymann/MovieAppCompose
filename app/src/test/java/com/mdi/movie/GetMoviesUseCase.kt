package com.mdi.movie

import com.mdi.movie.features.movieslist.data.model.MovieEntity
import com.mdi.movie.features.movieslist.data.model.MovieListResponseItem
import com.mdi.movie.features.movieslist.data.model.MoviesPagingResponse
import com.mdi.movie.features.movieslist.data.model.MoviesType
import com.mdi.movie.features.movieslist.data.repo.MovieRepository
import com.mdi.movie.features.movieslist.domain.GetMoviesUseCase
import com.mdi.movie.features.movieslist.domain.MovieListMapper
import com.mdi.movie.features.movieslist.domain.model.MovieParams
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class GetMoviesUseCaseTest {

    private val movieRepository: MovieRepository = Mockito.mock(MovieRepository::class.java)
    private val getMoviesUseCase = GetMoviesUseCase(movieRepository)

    @Test
    fun `invoke should emit local movies if available`() = runBlockingTest {
        // Arrange
        val localMovies = listOf(MovieEntity(1, "Test Movie", "2021-01-01", 4.5))
        Mockito.`when`(movieRepository.getMoviesFromLocal()).thenReturn(localMovies)

        // Act
        val result = getMoviesUseCase(MovieParams(MoviesType.Popular)).toList()

        // Assert
        assert(result.first().isSuccess)
        assertEquals(MovieListMapper.listToUiListOfMovieItem(localMovies), result.first().getOrNull())
    }

    @Test
    fun `invoke should fetch movies from remote if local is empty`() = runBlockingTest {
        // Arrange
        Mockito.`when`(movieRepository.getMoviesFromLocal()).thenReturn(emptyList())
        Mockito.`when`(movieRepository.getMoviesFromRemote(MovieParams(MoviesType.Popular))).thenReturn(
            MoviesPagingResponse(
                page = 1,
                moviesList = listOf(),
                totalPages = 2,
                totalResults = 2
            )
            )


        // Act
        val result = getMoviesUseCase(MovieParams(MoviesType.Popular)).toList()

        // Assert
        assert(result.first().isSuccess)
        Mockito.verify(movieRepository).saveMoviesToLocal(emptyList())
    }

    @Test
    fun `invoke should emit failure if remote fetch fails`() = runBlockingTest {
        // Arrange
        Mockito.`when`(movieRepository.getMoviesFromLocal()).thenReturn(emptyList())
        Mockito.`when`(movieRepository.getMoviesFromRemote(MovieParams(MoviesType.Popular))).thenThrow(RuntimeException("Network error"))

        // Act
        val result = getMoviesUseCase(MovieParams(MoviesType.Popular)).toList()

        // Assert
        assert(result.first().isFailure)
    }
}
