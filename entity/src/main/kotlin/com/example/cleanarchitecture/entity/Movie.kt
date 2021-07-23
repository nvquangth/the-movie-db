package com.example.cleanarchitecture.entity

data class Movie(
    val id: Int,
    val title: String? = null,
    val posterPath: String? = null,
    val backdropPath: String? = null,
    val voteAverage: Float? = null,
    val overview: String? = null,
    val releaseDate: Long? = null
) : Model()
