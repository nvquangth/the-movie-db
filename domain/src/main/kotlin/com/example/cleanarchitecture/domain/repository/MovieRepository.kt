package com.example.cleanarchitecture.domain.repository

import com.example.cleanarchitecture.entity.Movie

interface MovieRepository : Repository {

    suspend fun getMovie(movieId: Int, fromServer: Boolean): Movie?

    suspend fun searchMovie(q: String, page: Int, fromServer: Boolean): List<Movie>?

    suspend fun getPopularMovie(page: Int): List<Movie>?
}
