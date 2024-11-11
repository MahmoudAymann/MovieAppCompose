package com.mdi.movie.movieslist


import com.mdi.movie.features.movieslist.data.model.MoviesType
import com.mdi.movie.features.movieslist.domain.GetMoviesUseCase
import com.mdi.movie.features.movieslist.domain.model.MovieParams
import com.mdi.movie.features.movieslist.ui.MoviesContract
import com.mdi.movie.features.movieslist.ui.MoviesListViewModel
import com.mdi.movie.features.movieslist.ui.model.MovieItem
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MoviesListViewModelTest {

    private val getMoviesUseCase = mockk<GetMoviesUseCase>()
    private lateinit var viewModel: MoviesListViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = MoviesListViewModel(getMoviesUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test the initial state`() {
        //Arrange
        val expectedState = MoviesContract.State(
            isLoading = true, moviesList = listOf(), error = null
        )
        assertEquals(expectedState, viewModel.viewState.value)
    }


    @Test
    fun `test the getMovies Event and load some movies at start`() = runTest {
        //Arrange
        val movies = listOf(
            MovieItem(
                id = 7370,
                name = "Wilmer Gilmore",
                image = "inciderint",
                releaseDate = "mei",
                rating = 2.3
            )
        )
        val expectedState = MoviesContract.State(
            isLoading = false, moviesList = movies, error = null
        )
        val movieParams = MovieParams(type = MoviesType.Popular, page = 1)
        val event = MoviesContract.Event.GetMovies(movieParams)

        //Act
        coEvery { getMoviesUseCase(movieParams) }.returns(flow {
            emit(Result.success(movies))
        })
        viewModel.setEvent(event)

        //Assert
        assertEquals(expectedState, viewModel.viewState.value)
        coVerify { getMoviesUseCase(any()) }
    }


    @Test
    fun `test the LoadMore Event and load some movies paginated`() = runTest {
        //Arrange
        val movies = listOf(
            MovieItem(
                id = 7370,
                name = "Wilmer Gilmore",
                image = "inciderint",
                releaseDate = "mei",
                rating = 2.3
            )
        )
        val expectedState = MoviesContract.State(
            isLoading = false, moviesList = movies, error = null
        )
        val movieParams = MovieParams(type = MoviesType.Popular, page = 1)
        val event = MoviesContract.Event.LoadMore(MoviesType.Popular)

        //Act
        coEvery { getMoviesUseCase(movieParams) }.returns(flow {
            emit(Result.success(movies))
        })
        viewModel.setEvent(event)

        //Assert
        assertEquals(expectedState, viewModel.viewState.value)
        coVerify { getMoviesUseCase(any()) }
    }

    @Test
    fun `test the LoadMore Event and show an error`() = runTest {
        //Arrange
        val expectedState = MoviesContract.State(
            isLoading = false, moviesList = emptyList(), error = "Some Error"
        )
        val movieParams = MovieParams(type = MoviesType.Popular, page = 1)
        val event = MoviesContract.Event.LoadMore(MoviesType.Popular)

        //Act
        coEvery { getMoviesUseCase(movieParams) }.returns(flow {
            emit(Result.failure(Exception(expectedState.error)))
        })
        viewModel.setEvent(event)

        //Assert
        assertEquals(expectedState, viewModel.viewState.value)
        coVerify { getMoviesUseCase(any()) }
    }


    @Test
    fun `test MovieSelected Event and navigate`() = runTest {
        //Arrange
        val movieId = 1
        val effect = MoviesContract.Effect.NavigateToMovieDetails(movieId)

        //Act
        viewModel.setEvent(MoviesContract.Event.MovieSelected(movieId))
        //Assert
        assertEquals(effect, viewModel.effect.firstOrNull())

    }


}
