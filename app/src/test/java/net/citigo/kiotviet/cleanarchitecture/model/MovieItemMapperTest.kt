package com.example.cleanarchitecture.model

import android.content.Context
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.extension.mock
import com.example.cleanarchitecture.extension.whenever
import com.example.cleanarchitecture.factory.createMovie
import com.example.cleanarchitecture.factory.createMovieItem
import org.junit.Assert
import org.junit.Test

class MovieItemMapperTest {

    private val context = mock<Context>()

    private val movieItemMapper = MovieItemMapper(context)

    @Test
    fun `convert movie to movie item`() {
        val movie = createMovie()

        // given
        whenever(context.getString(R.string.format_yyyy_MM_dd)).thenReturn("yyyy-MM-dd")

        // when
        val movieItem = movieItemMapper.mapToPresentation(movie)

        // then
        Assert.assertEquals(movie.id, movieItem.id)
        Assert.assertEquals(movie.title, movieItem.title)
        Assert.assertEquals("2020-09-15", movieItem.releaseDate)
        Assert.assertEquals(movie.overview, movieItem.overview)
        Assert.assertEquals(80, movieItem.voteAverage)
        Assert.assertEquals(movie.backdropPath, movieItem.backdropPath)
        Assert.assertEquals(movie.posterPath, movieItem.posterPath)
    }

    @Test
    fun `convert movie item to movie`() {
        val movieItem = createMovieItem()

        // given
        whenever(context.getString(R.string.format_yyyy_MM_dd)).thenReturn("yyyy-MM-dd")

        // when
        val movie = movieItemMapper.mapToDomain(movieItem)

        // then
        Assert.assertEquals(movie.id, movieItem.id)
        Assert.assertEquals(movie.title, movieItem.title)
//        Assert.assertEquals(movie.releaseDate, 1600102800 * 1000L)
        Assert.assertEquals(movie.overview, movieItem.overview)
        Assert.assertEquals(movie.voteAverage, 0.8F)
        Assert.assertEquals(movie.backdropPath, movieItem.backdropPath)
        Assert.assertEquals(movie.posterPath, movieItem.posterPath)
    }
}
