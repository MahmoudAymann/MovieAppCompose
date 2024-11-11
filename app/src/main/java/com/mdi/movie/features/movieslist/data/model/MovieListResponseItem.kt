package com.mdi.movie.features.movieslist.data.model


import com.google.gson.annotations.SerializedName

data class MoviesPagingResponse(
    @SerializedName("page") val page: Int?,
    @SerializedName("results") val moviesList: List<MovieListResponseItem?>?,
    @SerializedName("total_pages") val totalPages: Int?,
    @SerializedName("total_results") val totalResults: Int?
)

data class MovieListResponseItem(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("adult") val adult: Boolean? = null,
    @SerializedName("backdrop_path") val backdropPath: String? = null,
    @SerializedName("original_language") val originalLanguage: String? = null,
    @SerializedName("original_title") val originalTitle: String? = null,
    @SerializedName("overview") val overview: String? = null,
    @SerializedName("poster_path") val posterPath: String? = null,
    @SerializedName("release_date") val releaseDate: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("vote_average") val voteAverage: Double? = null,
    @SerializedName("genres") val genres: List<GenresResponseItem>? = null,

    )

data class GenresResponseItem(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val name: String? = null,
)