package com.example.cleanarchitecture.data.network.response

import com.example.cleanarchitecture.data.model.MovieEntity
import com.google.gson.annotations.SerializedName
import javax.inject.Inject

data class MovieResponse(
    val id: Int,
    val title: String? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("vote_average")
    val voteAverage: Float? = null,
    val overview: String? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null
) : ResponseEntity

class MovieResponseEntityMapper @Inject constructor() : ResponseEntityMapper<MovieEntity, MovieResponse> {
    override fun mapToModelEntity(responseEntity: MovieResponse): MovieEntity = MovieEntity(
        id = responseEntity.id,
        title = responseEntity.title,
        posterPath = responseEntity.posterPath,
        backdropPath = responseEntity.backdropPath,
        voteAverage = responseEntity.voteAverage,
        overview = responseEntity.overview,
        releaseDate = responseEntity.releaseDate
    )

    override fun mapToResponseEntity(modelEntity: MovieEntity): MovieResponse = MovieResponse(
        id = modelEntity.id,
        title = modelEntity.title,
        posterPath = modelEntity.posterPath,
        backdropPath = modelEntity.backdropPath,
        voteAverage = modelEntity.voteAverage,
        overview = modelEntity.overview,
        releaseDate = modelEntity.releaseDate
    )
}
