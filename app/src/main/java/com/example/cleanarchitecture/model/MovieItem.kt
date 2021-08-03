package com.example.cleanarchitecture.model

import android.content.Context
import android.os.Parcelable
import com.bt.common.date.toTimeLong
import com.bt.common.date.toTimeString
import com.bt.presentation.base.model.ItemMapper
import com.bt.presentation.base.model.ModelItem
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.entity.Movie
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

@Parcelize
data class MovieItem(
    val id: Int,
    val title: String? = null,
    val posterPath: String? = null,
    val backdropPath: String? = null,
    val voteAverage: Int? = null,
    val overview: String? = null,
    val releaseDate: String? = null
) : ModelItem(), Parcelable

class MovieItemMapper @Inject constructor(@ApplicationContext private val context: Context) :
    ItemMapper<Movie, MovieItem> {
    override fun mapToPresentation(model: Movie): MovieItem = MovieItem(
        id = model.id,
        title = model.title,
        posterPath = model.posterPath,
        backdropPath = model.backdropPath,
        voteAverage = model.voteAverage?.times(10)?.toInt(),
        overview = model.overview,
        releaseDate = model.releaseDate?.toTimeString(context.getString(R.string.format_yyyy_MM_dd)) // convert time: long -> string
    )

    override fun mapToDomain(item: MovieItem): Movie = Movie(
        id = item.id,
        title = item.title,
        posterPath = item.posterPath,
        backdropPath = item.backdropPath,
        voteAverage = item.voteAverage?.div(10F),
        overview = item.overview,
        releaseDate = item.releaseDate?.toTimeLong(context.getString(R.string.format_yyyy_MM_dd)) // // convert time: string -> long
    )
}
