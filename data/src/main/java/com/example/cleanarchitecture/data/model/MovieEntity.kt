package com.example.cleanarchitecture.data.model

import android.content.Context
import com.bt.common.date.toTimeLong
import com.bt.common.date.toTimeString
import com.example.cleanarchitecture.data.R
import com.example.cleanarchitecture.entity.Movie
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

data class MovieEntity(
    val id: Int,
    val title: String? = null,
    val posterPath: String? = null,
    val backdropPath: String? = null,
    val voteAverage: Float? = null,
    val overview: String? = null,
    val releaseDate: String? = null
) : ModelEntity

class MovieEntityMapper @Inject constructor(@ApplicationContext private val context: Context) :
    EntityMapper<Movie, MovieEntity> {
    override fun mapToData(model: Movie): MovieEntity = MovieEntity(
        id = model.id,
        title = model.title,
        posterPath = model.posterPath,
        backdropPath = model.backdropPath,
        voteAverage = model.voteAverage,
        overview = model.overview,
        releaseDate = model.releaseDate?.toTimeString(context.getString(R.string.format_yyyy_MM_dd)) // convert time: long -> string
    )

    override fun mapToDomain(entity: MovieEntity): Movie = Movie(
        id = entity.id,
        title = entity.title,
        posterPath = entity.posterPath,
        backdropPath = entity.backdropPath,
        voteAverage = entity.voteAverage,
        overview = entity.overview,
        releaseDate = entity.releaseDate?.toTimeLong(context.getString(R.string.format_yyyy_MM_dd)) // convert time: string -> long
    )
}
