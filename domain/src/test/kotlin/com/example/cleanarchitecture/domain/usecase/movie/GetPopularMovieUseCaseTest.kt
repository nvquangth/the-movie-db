package com.example.cleanarchitecture.domain.usecase.movie

import com.example.cleanarchitecture.domain.base.BaseTest
import com.example.cleanarchitecture.domain.extension.mock
import com.example.cleanarchitecture.domain.extension.whenever
import com.example.cleanarchitecture.domain.factory.createMovies
import com.example.cleanarchitecture.domain.repository.MovieRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetPopularMovieUseCaseTest : BaseTest() {

    private val repository = mock<MovieRepository>()

    private lateinit var getPopularMovieUseCase: GetPopularMovieUseCase

    @Before
    fun setup() {
        getPopularMovieUseCase = GetPopularMovieUseCase(repository)
    }

    @Test
    fun `get popular`() {

        runBlockingTest {
            // given
            val param = GetPopularMovieUseCase.Params(page = 1)

            whenever(repository.getPopularMovie(1)).thenReturn(
                createMovies()
            )

            // when
            val movies = getPopularMovieUseCase.execute(param)

            // then
            Assert.assertEquals(2, movies.size)
        }
    }
}
