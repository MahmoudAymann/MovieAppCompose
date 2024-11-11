package com.mdi.movie.core.localdb.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mdi.movie.core.localdb.room.dao.MovieDao
import com.mdi.movie.features.movies.data.model.GenresConverter
import com.mdi.movie.features.movies.data.model.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
@TypeConverters(GenresConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}