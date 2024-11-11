package com.mdi.movie.movieslist

import com.mdi.movie.features.movieslist.data.model.MovieEntity
import com.mdi.movie.features.movieslist.data.model.MovieListResponseItem
import com.mdi.movie.features.movieslist.data.model.MoviesPagingResponse
import com.mdi.movie.features.movieslist.data.model.MoviesType
import com.mdi.movie.features.movieslist.data.repo.MovieRepository
import com.mdi.movie.features.movieslist.domain.GetMoviesUseCase
import com.mdi.movie.features.movieslist.domain.MovieListMapper
import com.mdi.movie.features.movieslist.domain.model.MovieParams
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetMoviesUseCaseTest {

    private val movieRepository: MovieRepository = mockk()
    private lateinit var getMoviesUseCase: GetMoviesUseCase

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        getMoviesUseCase = GetMoviesUseCase(movieRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `invoke should emit local movies if available first`() = runTest {
        // Arrange
        val localMovies = listOf(
            MovieEntity(
                id = 1, name = "Test Movie", releaseDate = "2021-01-01", rating = 4.5, image = ""
            )
        )

        // Act
        coEvery { movieRepository.getMoviesFromLocal() } returns localMovies
        val result = getMoviesUseCase(MovieParams(type = MoviesType.Popular, page = 1)).toList()
        // Assert
        assertEquals(
            MovieListMapper.listToUiListOfMovieItem(localMovies), result.first().getOrNull()
        )
    }

    @Test
    fun `invoke should fetch movies from remote if local is empty`() = runTest {
        // Arrange
        val movieRes = MovieListResponseItem(
            adult = null,
            backdropPath = null,
            id = null,
            originalLanguage = null,
            originalTitle = null,
            overview = null,
            posterPath = null,
            releaseDate = null,
            title = null,
            voteAverage = null
        )
        val response = MoviesPagingResponse(
            page = 1, moviesList = listOf(movieRes), totalPages = 1, totalResults = 1
        )
        val entityMovies = listOf(MovieListMapper.toMovieEntity(movieRes))


        // Act

        coEvery { movieRepository.getMoviesFromLocal() } returns emptyList()
        coEvery { movieRepository.getMoviesFromRemote(MovieParams(MoviesType.Popular)) } returns response
        coEvery { movieRepository.saveMoviesToLocal(entityMovies) } returns Unit

        val result = getMoviesUseCase(MovieParams(MoviesType.Popular)).toList()
        // Assert
        assertEquals(
            result.first().getOrNull(),
            MovieListMapper.listToUiListOfMovieItem(entityMovies)
        )
    }

    @Test
    fun `invoke should emit failure if remote fetch fails`() = runTest {
        // Arrange
        val error = "Runtime Exception"

        // Act
        coEvery { movieRepository.getMoviesFromLocal() } returns emptyList()
        coEvery { movieRepository.getMoviesFromRemote(MovieParams(MoviesType.Popular)) } throws Exception(
            error
        )

        val result = getMoviesUseCase(MovieParams(MoviesType.Popular)).toList()

        // Assert
        assert(result.first().isFailure)
    }
}

