package com.example.cleanarchitecture.data.di

import android.content.Context
import androidx.room.Room
import com.example.cleanarchitecture.data.Constants
import com.example.cleanarchitecture.data.database.room.AppDatabase
import com.example.cleanarchitecture.data.database.room.dao.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, Constants.DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideMovieDao(database: AppDatabase): MovieDao = database.movieDao()
}
