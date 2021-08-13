package com.example.cleanarchitecture.domain.usecase.movie

import com.example.cleanarchitecture.domain.repository.MovieRepository
import com.example.cleanarchitecture.domain.usecase.UseCase
import com.example.cleanarchitecture.entity.Movie
import com.example.cleanarchitecture.entity.exception.CleanException
import com.example.cleanarchitecture.entity.exception.CleanExceptionType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchMovieUseCase @Inject constructor(
    private val repository: MovieRepository
) : UseCase<SearchMovieUseCase.Params, Flow<List<Movie>>>() {

    data class Params(val q: String, val page: Int, val fromServer: Boolean)

    override suspend fun execute(param: Params?): Flow<List<Movie>> = param?.let {
        repository.searchMovie(param.q, param.page)
    } ?: throw CleanException(CleanExceptionType.PARAM_NULL)
}
