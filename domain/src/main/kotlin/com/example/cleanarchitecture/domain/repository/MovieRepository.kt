package com.example.cleanarchitecture.domain.repository

import com.example.cleanarchitecture.entity.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository : Repository {

    fun getMovie(movieId: Int): Flow<Movie>

    fun searchMovie(q: String, page: Int): Flow<List<Movie>>

    fun getPopularMovie(page: Int): Flow<List<Movie>>
}
