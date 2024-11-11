package com.mdi.movie.features.movieslist.data.model


import com.google.gson.annotations.SerializedName

data class MoviesPagingResponse(
    @SerializedName("page") val page: Int?,
    @SerializedName("results") val moviesList: List<MovieListResponseItem?>?,
    @SerializedName("total_pages") val totalPages: Int?,
    @SerializedName("total_results") val totalResults: Int?
)

data class MovieListResponseItem(
    @SerializedName("adult") val adult: Boolean?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("id") val id: Int?,
    @SerializedName("original_language") val originalLanguage: String?,
    @SerializedName("original_title") val originalTitle: String?,
    @SerializedName("overview") val overview: String?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("vote_average") val voteAverage: Double?,
    @SerializedName("genres") val genres: List<GenresResponseItem>?,

)

data class GenresResponseItem(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val name: String? = null,
)