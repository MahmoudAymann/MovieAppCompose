package com.mdi.movie.features.movieslist.data.datasource.remote

import com.mdi.movie.core.network.ApiServices
import com.mdi.movie.features.movieslist.data.model.MovieListResponseItem
import com.mdi.movie.features.movieslist.data.model.MoviesPagingResponse
import com.mdi.movie.features.movieslist.data.model.MoviesType
import com.mdi.movie.features.movieslist.domain.model.MovieParams
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(private val apiServices: ApiServices) :
    MovieRemoteDataSource {

    override suspend fun getMovies(movieParams: MovieParams): MoviesPagingResponse {
        return apiServices.getMovies(movieParams.type.key, page = movieParams.page)
    }

    override suspend fun getMovieDetails(movieId: Int): MovieListResponseItem {
        return apiServices.getMovieDetails(movieId)
    }


}