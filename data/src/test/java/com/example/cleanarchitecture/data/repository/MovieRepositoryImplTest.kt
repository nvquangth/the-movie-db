package com.example.cleanarchitecture.data.repository

import android.content.Context
import com.example.cleanarchitecture.data.R
import com.example.cleanarchitecture.data.base.BaseTest
import com.example.cleanarchitecture.data.extension.mock
import com.example.cleanarchitecture.data.extension.whenever
import com.example.cleanarchitecture.data.factory.ErrorResponseFactory
import com.example.cleanarchitecture.data.factory.MovieFactory
import com.example.cleanarchitecture.data.model.MovieEntityMapper
import com.example.cleanarchitecture.data.network.api.MovieApi
import com.example.cleanarchitecture.domain.repository.MovieRepository
import com.example.cleanarchitecture.entity.exception.CleanException
import com.example.cleanarchitecture.entity.exception.CleanExceptionType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import java.net.UnknownHostException

@ExperimentalCoroutinesApi
class MovieRepositoryImplTest : BaseTest() {

    private val context = mock<Context>()

    private val api = mock<MovieApi>()

    private val movieEntityMapper = MovieEntityMapper(context)

    private lateinit var movieRepository: MovieRepository

    @Before
    fun setup() {
        movieRepository = MovieRepositoryImpl(api, movieEntityMapper)
    }

    @Test
    fun `get movie`() {
        val movieId = 1111
        val fromServer = true
        val movieEntity = MovieFactory.MOVIE_ENTITY

        runBlockingTest {
            // given
            whenever(api.getMovie(movieId)).thenReturn(movieEntity)
            whenever(context.getString(R.string.format_yyyy_MM_dd)).thenReturn("yyyy-MM-dd")

            // when
            val result = movieRepository.getMovie(movieId, fromServer)
                ?.let { movieEntityMapper.mapToData(it) }

            // then
            Assert.assertEquals(movieEntity, result)
        }
    }

    @Test
    fun `get popular movie success`() {
        val page = 1
        val movieResponse = MovieFactory.MOVIES_RESPONSE

        runBlockingTest {
            // given
            whenever(api.getPopularMovie(page)).thenReturn(movieResponse)
            whenever(context.getString(R.string.format_yyyy_MM_dd)).thenReturn("yyyy-MM-dd")

            // when
            val result =
                movieRepository.getPopularMovie(page)?.map { movieEntityMapper.mapToData(it) }

            // then
            Assert.assertEquals(movieResponse.results, result)
        }
    }

    @Test
    fun `get popular movie fail`() {
        val page = 1
        var exception: Throwable? = null

        runBlockingTest {
            // given
            whenever(api.getPopularMovie(page)).thenAnswer {
                throw UnknownHostException()
            }

            // when
            runCatching {
                movieRepository.getPopularMovie(page)
            }.getOrElse {
                exception = it
                null
            }

            // then
            Assert.assertEquals(
                CleanExceptionType.NETWORK_NO_CONNECTION,
                (exception as CleanException).type
            )
        }
    }

    @Test
    fun `search movie success`() {
        val query = "dead pool"
        val page = 1
        val fromServer = true
        val movieResponse = MovieFactory.MOVIES_RESPONSE

        runBlockingTest {
            // given
            whenever(api.searchMovies(query, page)).thenReturn(movieResponse)
            whenever(context.getString(R.string.format_yyyy_MM_dd)).thenReturn("yyyy-MM-dd")

            // when
            val result = movieRepository.searchMovie(query, page, fromServer)
                ?.map { movieEntityMapper.mapToData(it) }

            // then
            Assert.assertEquals(movieResponse.results, result)
        }
    }

    @Test
    fun `search movie fail`() {
        val query = "dead pool"
        val page = 1
        var exception: Throwable? = null
        val errorResponse = ErrorResponseFactory.UNKNOWN

        runBlockingTest {
            // given
            whenever(api.searchMovies(query, page)).thenAnswer {
                throw HttpException(errorResponse)
            }

            // when
            try {
                movieRepository.searchMovie(query, page, true)
            } catch (e: Throwable) {
                exception = e
            }

            // then
            Assert.assertEquals(CleanExceptionType.UNKNOWN, (exception as CleanException).type)
        }
    }
}
