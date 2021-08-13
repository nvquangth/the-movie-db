package com.example.cleanarchitecture.domain.usecase.movie

import com.example.cleanarchitecture.domain.repository.MovieRepository
import com.example.cleanarchitecture.domain.usecase.UseCase
import com.example.cleanarchitecture.entity.Movie
import com.example.cleanarchitecture.entity.exception.CleanException
import com.example.cleanarchitecture.entity.exception.CleanExceptionType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDetailMovieUseCase @Inject constructor(
    private val repository: MovieRepository
) : UseCase<GetDetailMovieUseCase.Params, Flow<Movie>>() {

    data class Params(val movieId: Int, val fromServer: Boolean)

    override suspend fun execute(param: Params?): Flow<Movie> = param?.let {
        repository.getMovie(param.movieId)
    } ?: throw CleanException(CleanExceptionType.PARAM_NULL)
}
