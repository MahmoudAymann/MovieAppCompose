package com.mdi.movie

import com.mdi.movie.features.movieslist.data.model.MovieEntity
import com.mdi.movie.features.movieslist.data.model.MoviesType
import com.mdi.movie.features.movieslist.data.repo.MovieRepository
import com.mdi.movie.features.movieslist.domain.GetMoviesUseCase
import com.mdi.movie.features.movieslist.domain.model.MovieParams
import com.mdi.movie.features.movieslist.ui.MoviesContract
import com.mdi.movie.features.movieslist.ui.MoviesListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MoviesListViewModelTest {
/*
   // todo fix test case
    @Mock
    private lateinit var movieRepository: MovieRepository // Mocking the repository instead of use case
    private lateinit var getMoviesUseCase: GetMoviesUseCase
    private lateinit var viewModel: MoviesListViewModel
    private val testDispatcher = StandardTestDispatcher()


    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher) // Set Main to the test dispatcher
        getMoviesUseCase = GetMoviesUseCase(movieRepository)
        viewModel = MoviesListViewModel(getMoviesUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // Reset Main dispatcher to the original
    }

    @Test
    fun `when ResetAndGetMovies event is triggered, movies should be fetched`() = runTest {
        val params = MovieParams(MoviesType.Popular)
        val movieEntities = listOf(MovieEntity(1, "Mocked Movie", "2022-01-01", 4.5))

        Mockito.`when` (movieRepository.getMoviesFromLocal()).thenReturn(movieEntities)
        viewModel.setEvent(MoviesContract.Event.ResetAndGetMovies(params))

        verify(movieRepository).getMoviesFromLocal()
    }

    @Test
    fun `when MovieSelected event is triggered, navigation effect should be set`() = runTest {
        val movieId = 1
        viewModel.setEvent(MoviesContract.Event.MovieSelected(movieId))

        val effects = mutableListOf<MoviesContract.Effect>()
        val job = launch { viewModel.effect.toList(effects) }

        assert(effects.contains(MoviesContract.Effect.NavigateToMovieDetails(movieId)))
        job.cancel()
    }

 */
}
