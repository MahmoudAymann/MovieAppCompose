package com.mdi.movie.core.network


import com.mdi.movie.features.movies.movieslist.data.model.MovieListResponseItem
import com.mdi.movie.features.movies.movieslist.data.model.MoviesPagingResponse
import com.mdi.movie.features.movies.movieslist.data.model.MoviesType
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {

    companion object{
        private const val GET_MOVIES_LIST = "movie/{key}"
        private const val GET_MOVIE_DETAILS = "movie/{movieId}"
    }

    @GET(GET_MOVIES_LIST)
    suspend fun getMovies(
        @Path("key") key: String = MoviesType.NowPlaying.key,
        @Query("language") language: String = "en",
        @Query("page") page: Int = 1
    ): MoviesPagingResponse

    @GET(GET_MOVIE_DETAILS)
    suspend fun getMovieDetails(
        @Path("movieId") key: Int,
    ): MovieListResponseItem

}