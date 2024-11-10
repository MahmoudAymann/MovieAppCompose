package com.mdi.movie.core.localdb.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mdi.movie.core.localdb.room.dao.MovieDao
import com.mdi.movie.features.movieslist.data.model.MovieEntity

@Database(entities = [MovieEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}