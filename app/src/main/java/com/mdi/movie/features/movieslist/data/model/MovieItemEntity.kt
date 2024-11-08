package com.mdi.movie.features.movieslist.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val releaseDate: String,
    val rating: Double
)