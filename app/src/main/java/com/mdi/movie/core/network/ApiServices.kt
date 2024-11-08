package com.mdi.movie.core.network

import com.mdi.movie.BuildConfig
import com.mdi.movie.features.movieslist.data.model.MoviesPagingResponse
import com.mdi.movie.features.movieslist.data.model.MoviesType
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {

    @GET("movie/{key}")
    suspend fun getMovies(
        @Path("key") key: String = MoviesType.NowPlaying.key,
        @Query("api_key") apiKey: String = BuildConfig.IMDB_API_KEY,
        @Query("language") language: String = "en",
        @Query("page") page: Int = 1
    ): MoviesPagingResponse

}