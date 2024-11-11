package com.mdi.movie.movies.moviedetails.ui

import com.mdi.movie.features.movies.moviedetails.domain.GetMovieDetailsUseCase
import com.mdi.movie.features.movies.moviedetails.ui.MovieDetailsContractor
import com.mdi.movie.features.movies.moviedetails.ui.MovieDetailsViewModel
import com.mdi.movie.features.movies.moviedetails.ui.model.MovieDetails
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class MovieDetailsViewModelTest {

    private val getMovieDetailsUseCase = mockk<GetMovieDetailsUseCase>()
    private lateinit var viewModel: MovieDetailsViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = MovieDetailsViewModel(getMovieDetailsUseCase)
    }


    @Test
    fun `test init state`() {
        //Arrange
        val expectedState = MovieDetailsContractor.State()
        //Assert
        assertEquals(expectedState, viewModel.viewState.value)
    }


    @Test
    fun `handle event GetDetails and return success result`() = runTest {
        //Arrange
        val movieDetails = MovieDetails(
            id = 3479,
            name = "Dixie Lynn",
            image = "sit",
            releaseDate = "scripta",
            rating = 20.21,
            overview = "equidem",
            genres = listOf()
        )
        val expectedState = MovieDetailsContractor.State(
            isLoading = false, movieDetails = movieDetails, error = null
        )
        val event = MovieDetailsContractor.Event.GetDetails(movieDetails.id)

        //Act
        coEvery { getMovieDetailsUseCase(movieDetails.id) }.returns(flow {
            emit(Result.success(movieDetails))
        })
        viewModel.setEvent(event)

        //Assert
        Assert.assertEquals(expectedState, viewModel.viewState.value)
        coVerify { getMovieDetailsUseCase(any()) }
    }

    @Test
    fun `handle event GetDetails and return exception`() = runTest {
        //Arrange
        val exception = Exception("error")
        val expectedState = MovieDetailsContractor.State(
            isLoading = false, error = exception.message
        )
        val event = MovieDetailsContractor.Event.GetDetails(1)

        //Act
        coEvery { getMovieDetailsUseCase(1) }.returns(flow {
            emit(Result.failure(exception))
        })
        viewModel.setEvent(event)

        //Assert
        Assert.assertEquals(expectedState, viewModel.viewState.value)
        coVerify { getMovieDetailsUseCase(any()) }
    }


}