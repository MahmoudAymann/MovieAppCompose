package com.mdi.movie.movies.movieslist.ui


import com.mdi.movie.features.movies.movieslist.data.model.MoviesType
import com.mdi.movie.features.movies.movieslist.domain.GetMoviesUseCase
import com.mdi.movie.features.movies.movieslist.domain.model.MovieParams
import com.mdi.movie.features.movies.movieslist.ui.MovieListContract
import com.mdi.movie.features.movies.movieslist.ui.MovieListViewModel
import com.mdi.movie.features.movies.movieslist.ui.model.MovieItem
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
class MovieListViewModelTest {

    private val getMoviesUseCase = mockk<GetMoviesUseCase>()
    private lateinit var viewModel: MovieListViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = MovieListViewModel(getMoviesUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test the initial state`() {
        //Arrange
        val expectedState = MovieListContract.State(
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
        val expectedState = MovieListContract.State(
            isLoading = false, moviesList = movies, error = null
        )
        val movieParams = MovieParams(type = MoviesType.Popular, page = 1)
        val event = MovieListContract.Event.GetMovies(movieParams)

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
        val expectedState = MovieListContract.State(
            isLoading = false, moviesList = movies, error = null
        )
        val movieParams = MovieParams(type = MoviesType.Popular, page = 1)
        val event = MovieListContract.Event.LoadMore(MoviesType.Popular)

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
        val expectedState = MovieListContract.State(
            isLoading = false, moviesList = emptyList(), error = "Some Error"
        )
        val movieParams = MovieParams(type = MoviesType.Popular, page = 1)
        val event = MovieListContract.Event.LoadMore(MoviesType.Popular)

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
        val effect = MovieListContract.Effect.NavigateToMovieDetails(movieId)

        //Act
        viewModel.setEvent(MovieListContract.Event.MovieSelected(movieId))
        //Assert
        assertEquals(effect, viewModel.effect.firstOrNull())

    }


}
