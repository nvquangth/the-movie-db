package com.example.cleanarchitecture.data.repository

import com.example.cleanarchitecture.data.database.room.dao.MovieDao
import com.example.cleanarchitecture.data.database.room.entity.MovieRoomEntityMapper
import com.example.cleanarchitecture.data.model.MovieEntityMapper
import com.example.cleanarchitecture.data.network.api.MovieApi
import com.example.cleanarchitecture.data.network.response.MovieResponseEntityMapper
import com.example.cleanarchitecture.domain.repository.MovieRepository
import com.example.cleanarchitecture.entity.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi,
    private val movieDao: MovieDao,
    private val movieEntityMapper: MovieEntityMapper,
    private val movieResponseEntityMapper: MovieResponseEntityMapper,
    private val movieRoomEntityMapper: MovieRoomEntityMapper,
) : MovieRepository {
    override fun getMovie(movieId: Int): Flow<Movie> {
        TODO("Not yet implemented")
    }

    override fun searchMovie(q: String, page: Int): Flow<List<Movie>> {
        TODO("Not yet implemented")
    }

    override fun getPopularMovie(page: Int): Flow<List<Movie>> = flow {

        val movies = api.getPopularMovie(page).results ?: emptyList()

        movies.map {
            movieResponseEntityMapper.mapToModelEntity(it)
        }.run {
            map { movieRoomEntityMapper.mapToRoomEntity(it) }.also { movieDao.insert(it) }

            map { movieEntityMapper.mapToDomain(it) }.also { emit(it) }
        }
    }.catch {
        emitAll(
            movieDao.getAll().map {
                it.map { movieRoomEntityMapper.mapToModelEntity(it) }
                    .map { movieEntityMapper.mapToDomain(it) }
            }
        )
    }
}
