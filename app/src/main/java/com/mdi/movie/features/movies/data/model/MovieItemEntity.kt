package com.mdi.movie.features.movies.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val image: String,
    val releaseDate: String,
    val rating: Double,
    val overview: String,
    val genres: List<String>
)