package com.example.cleanarchitecture.data.database.room.entity

import android.content.Context
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bt.common.date.toTimeLong
import com.bt.common.date.toTimeString
import com.example.cleanarchitecture.data.R
import com.example.cleanarchitecture.data.model.MovieEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@Entity(tableName = "movie")
data class MovieRoomEntity(
    @PrimaryKey
    val id: Int,
    val title: String? = null,
    val posterPath: String? = null,
    val backdropPath: String? = null,
    val voteAverage: Float? = null,
    val overview: String? = null,
    val releaseDate: Long? = null
) : RoomEntity

class MovieRoomEntityMapper @Inject constructor(@ApplicationContext private val context: Context) : RoomEntityMapper<MovieEntity, MovieRoomEntity> {
    override fun mapToModelEntity(roomEntity: MovieRoomEntity): MovieEntity = MovieEntity(
        id = roomEntity.id,
        title = roomEntity.title,
        posterPath = roomEntity.posterPath,
        backdropPath = roomEntity.backdropPath,
        voteAverage = roomEntity.voteAverage,
        overview = roomEntity.overview,
        releaseDate = roomEntity.releaseDate?.toTimeString(context.getString(R.string.format_yyyy_MM_dd))
    )

    override fun mapToRoomEntity(modelEntity: MovieEntity): MovieRoomEntity = MovieRoomEntity(
        id = modelEntity.id,
        title = modelEntity.title,
        posterPath = modelEntity.posterPath,
        backdropPath = modelEntity.backdropPath,
        voteAverage = modelEntity.voteAverage,
        overview = modelEntity.overview,
        releaseDate = modelEntity.releaseDate?.toTimeLong(context.getString(R.string.format_yyyy_MM_dd))
    )
}
