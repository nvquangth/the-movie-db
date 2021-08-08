package com.example.cleanarchitecture.data.repository

import com.example.cleanarchitecture.data.model.MovieEntityMapper
import com.example.cleanarchitecture.data.network.api.MovieApi
import com.example.cleanarchitecture.data.network.response.MovieResponseEntityMapper
import com.example.cleanarchitecture.domain.repository.MovieRepository
import com.example.cleanarchitecture.entity.Movie
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi,
    private val movieEntityMapper: MovieEntityMapper,
    private val movieResponseEntityMapper: MovieResponseEntityMapper
) : MovieRepository {
    override suspend fun getMovie(movieId: Int, fromServer: Boolean): Movie = api.getMovie(movieId)
        .let { movieResponseEntityMapper.mapToModelEntity(it) }
        .let { movieEntityMapper.mapToDomain(it) }

    override suspend fun searchMovie(q: String, page: Int, fromServer: Boolean): List<Movie>? = api.searchMovies(q, page).results
        ?.map { movieResponseEntityMapper.mapToModelEntity(it) }
        ?.map { movieEntityMapper.mapToDomain(it) }

    override suspend fun getPopularMovie(page: Int): List<Movie>? = api.getPopularMovie(page).results
        ?.map { movieResponseEntityMapper.mapToModelEntity(it) }
        ?.map { movieEntityMapper.mapToDomain(it) }
}
