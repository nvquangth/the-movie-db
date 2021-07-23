package com.example.cleanarchitecture.domain.usecase.movie

import com.example.cleanarchitecture.domain.repository.MovieRepository
import com.example.cleanarchitecture.domain.usecase.UseCase
import com.example.cleanarchitecture.entity.Movie
import com.example.cleanarchitecture.entity.exception.CleanException
import com.example.cleanarchitecture.entity.exception.CleanExceptionType
import javax.inject.Inject

class GetPopularMovieUseCase @Inject constructor(
    private val repository: MovieRepository
) : UseCase<GetPopularMovieUseCase.Params, List<Movie>>() {

    data class Params(val page: Int)

    override suspend fun execute(param: Params?): List<Movie> = param?.let {
        repository.getPopularMovie(it.page)
    } ?: throw CleanException(CleanExceptionType.PARAM_NULL)
}
