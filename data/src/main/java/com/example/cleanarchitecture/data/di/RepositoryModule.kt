package com.example.cleanarchitecture.data.di

import com.example.cleanarchitecture.data.repository.AuthRepositoryImpl
import com.example.cleanarchitecture.data.repository.MovieRepositoryImpl
import com.example.cleanarchitecture.domain.repository.AuthRepository
import com.example.cleanarchitecture.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Singleton
    @Binds
    abstract fun bindMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository
}
