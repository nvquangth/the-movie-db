package com.example.cleanarchitecture.data.repository

import com.example.cleanarchitecture.data.model.MovieEntityMapper
import com.example.cleanarchitecture.data.remote.MovieApi
import com.example.cleanarchitecture.domain.repository.MovieRepository
import com.example.cleanarchitecture.entity.Movie
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi,
    private val movieEntityMapper: MovieEntityMapper
) : MovieRepository {
    override suspend fun getMovie(movieId: Int, fromServer: Boolean): Movie = api.getMovie(movieId).let {
        movieEntityMapper.mapToDomain(it)
    }

    override suspend fun searchMovie(q: String, page: Int, fromServer: Boolean): List<Movie>? = api.searchMovies(q, page).results?.map {
        movieEntityMapper.mapToDomain(it)
    }

    override suspend fun getPopularMovie(page: Int): List<Movie>? = api.getPopularMovie(page).results?.map {
        movieEntityMapper.mapToDomain(it)
    }
}
