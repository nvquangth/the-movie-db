package com.example.cleanarchitecture.domain.usecase.movie

import com.example.cleanarchitecture.domain.repository.MovieRepository
import com.example.cleanarchitecture.domain.usecase.UseCase
import com.example.cleanarchitecture.entity.Movie
import com.example.cleanarchitecture.entity.exception.CleanException
import com.example.cleanarchitecture.entity.exception.CleanExceptionType
import javax.inject.Inject

class GetDetailMovieUseCase @Inject constructor(
    private val repository: MovieRepository
) : UseCase<GetDetailMovieUseCase.Params, Movie>() {

    data class Params(val movieId: Int, val fromServer: Boolean)

    override suspend fun execute(param: Params?): Movie = param?.let {
        repository.getMovie(param.movieId, param.fromServer)
    } ?: throw CleanException(CleanExceptionType.PARAM_NULL)
}
