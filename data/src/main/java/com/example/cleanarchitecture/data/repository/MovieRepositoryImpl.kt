package com.example.cleanarchitecture.data.repository

import com.example.cleanarchitecture.data.extension.throwCleanException
import com.example.cleanarchitecture.data.model.MovieEntityMapper
import com.example.cleanarchitecture.data.remote.MovieApi
import com.example.cleanarchitecture.domain.repository.MovieRepository
import com.example.cleanarchitecture.entity.Movie
import com.example.cleanarchitecture.entity.exception.CleanException
import com.example.cleanarchitecture.entity.exception.CleanExceptionType
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi,
    private val movieEntityMapper: MovieEntityMapper
) : MovieRepository {
    override suspend fun getMovie(movieId: Int, fromServer: Boolean): Movie =
        api.getMovie(movieId).let { movieEntityMapper.mapToDomain(it) }

    override suspend fun searchMovie(q: String, page: Int, fromServer: Boolean): List<Movie>? {

        try {
            val response = api.searchMovies(q, page)
            return response.results?.map { movieEntityMapper.mapToDomain(it) }
        } catch (e: Exception) {
            e.throwCleanException()
        }
        throw CleanException(CleanExceptionType.DATA_NULL_OR_EMPTY)
    }

    override suspend fun getPopularMovie(page: Int): List<Movie>? {
        try {
            val response = api.getPopularMovie(page)

            return response.results?.map { movieEntityMapper.mapToDomain(it) }
        } catch (e: Exception) {
            e.throwCleanException()
        }
        throw CleanException(CleanExceptionType.DATA_NULL_OR_EMPTY)
    }
}
