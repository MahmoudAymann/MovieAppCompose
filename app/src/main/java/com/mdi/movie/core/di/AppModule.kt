package com.mdi.movie.core.di

import android.content.Context
import androidx.room.Room
import com.mdi.movie.core.localdb.room.AppDatabase
import com.mdi.movie.core.utils.AppConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            AppConstants.ROOM_DB_NAME
        ).build()
    }
}