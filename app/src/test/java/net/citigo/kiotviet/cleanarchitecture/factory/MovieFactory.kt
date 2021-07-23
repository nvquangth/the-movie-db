package com.example.cleanarchitecture.factory

import com.example.cleanarchitecture.entity.Movie
import com.example.cleanarchitecture.model.MovieItem

fun createMovie() = Movie(
    id = 718444,
    title = "Bill & Ted Face the Musi",
    releaseDate = 1600128000 * 1000L,
    overview = "Yet to fulfill their rock and roll destiny, the now middle-aged best friends Bill and Ted set out on a new adventure when a visitor from the future warns them that only their song can save life as we know it. Along the way, they are helped by their daughters, a new batch of historical figures and a few music legends—to seek the song that will set their world right and bring harmony to the universe.",
    voteAverage = 8.0F,
    backdropPath = "/oazPqs1z78LcIOFslbKtJLGlueo.jpg",
    posterPath = "/4V2nTPfeB59TcqJcUfQ9ziTi7VN.jpg"
)

fun createMovieItem() = MovieItem(
    id = 718444,
    title = "Bill & Ted Face the Musi",
    releaseDate = "2020-09-15",
    overview = "Yet to fulfill their rock and roll destiny, the now middle-aged best friends Bill and Ted set out on a new adventure when a visitor from the future warns them that only their song can save life as we know it. Along the way, they are helped by their daughters, a new batch of historical figures and a few music legends—to seek the song that will set their world right and bring harmony to the universe.",
    voteAverage = 8,
    backdropPath = "/oazPqs1z78LcIOFslbKtJLGlueo.jpg",
    posterPath = "/4V2nTPfeB59TcqJcUfQ9ziTi7VN.jpg"
)
