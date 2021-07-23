package com.example.cleanarchitecture.domain.usecase.movie

import com.example.cleanarchitecture.domain.repository.MovieRepository
import com.example.cleanarchitecture.domain.usecase.UseCase
import com.example.cleanarchitecture.entity.Movie
import com.example.cleanarchitecture.entity.exception.CleanException
import com.example.cleanarchitecture.entity.exception.CleanExceptionType
import javax.inject.Inject

class SearchMovieUseCase @Inject constructor(
    private val repository: MovieRepository
) : UseCase<SearchMovieUseCase.Params, List<Movie>>() {

    data class Params(val q: String, val page: Int, val fromServer: Boolean)

    override suspend fun execute(param: Params?): List<Movie> = param?.let {
        repository.searchMovie(param.q, param.page, param.fromServer)
    } ?: throw CleanException(CleanExceptionType.PARAM_NULL)
}
