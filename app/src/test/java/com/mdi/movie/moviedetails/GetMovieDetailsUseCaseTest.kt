package com.mdi.movie.moviedetails

import com.mdi.movie.features.moviedetails.domain.GetMovieDetailsUseCase
import com.mdi.movie.features.movieslist.data.model.MovieEntity
import com.mdi.movie.features.movieslist.data.model.MovieListResponseItem
import com.mdi.movie.features.movieslist.data.model.MoviesPagingResponse
import com.mdi.movie.features.movieslist.data.model.MoviesType
import com.mdi.movie.features.movieslist.data.repo.MovieRepository
import com.mdi.movie.features.movieslist.domain.MovieListMapper
import com.mdi.movie.features.movieslist.domain.MovieListMapper.toMovieDetails
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
    private lateinit var getMovieDetailsUseCase: GetMovieDetailsUseCase

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        getMovieDetailsUseCase = GetMovieDetailsUseCase(movieRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `invoke should emit local movie if available first`() = runTest {
        // Arrange
        val localMovie = MovieEntity(
            id = 1,
            name = "Test Movie",
            releaseDate = "2021-01-01",
            rating = 4.5,
            image = "",
            overview = "",
            genres = listOf()
        )

        // Act
        coEvery { movieRepository.getMovieDetailsFromLocal(localMovie.id) } returns localMovie
        val result =
            getMovieDetailsUseCase(localMovie.id).toList()
        // Assert
        assertEquals(
            localMovie.toMovieDetails(), result.first().getOrNull()
        )
    }

    @Test
    fun `invoke should fetch movie details from remote if local is empty`() = runTest {
        // Arrange
        val movieRes = MovieListResponseItem(
            adult = null,
            backdropPath = null,
            id = 123,
            originalLanguage = null,
            originalTitle = null,
            overview = null,
            posterPath = null,
            releaseDate = null,
            title = null,
            voteAverage = null,
            genres = listOf()
        )
        // Act
        coEvery { movieRepository.getMovieDetailsFromLocal(movieRes.id!!) } returns null
        coEvery { movieRepository.getMovieDetailsFromRemote(movieRes.id!!) } returns movieRes

        val result = getMovieDetailsUseCase(movieRes.id!!).toList()
        // Assert
        assertEquals(
            result.first().getOrNull(), MovieListMapper.toMovieEntity(movieRes).toMovieDetails()
        )
    }

    @Test
    fun `invoke should emit failure if remote fetch fails`() = runTest {
        // Arrange
        val error = "Runtime Exception"


        // Act
        coEvery { movieRepository.getMovieDetailsFromLocal(1) } returns null
        coEvery { movieRepository.getMovieDetailsFromRemote(1) } throws Exception(
            error
        )

        val result = getMovieDetailsUseCase(1).toList()

        // Assert
        assert(result.first().isFailure)
    }
}

