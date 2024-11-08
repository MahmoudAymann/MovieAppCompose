package com.mdi.movie.features.movieslist.data.datasource.remote

import com.mdi.movie.core.network.ApiServices
import com.mdi.movie.features.movieslist.data.model.MoviesPagingResponse
import com.mdi.movie.features.movieslist.data.model.MoviesType
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(private val apiServices: ApiServices) :
    MovieRemoteDataSource {

    override suspend fun getMovies(type: MoviesType, page: Int): MoviesPagingResponse {
        return apiServices.getMovies(type.key, page = page)
    }

}