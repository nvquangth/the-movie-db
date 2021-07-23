package com.example.cleanarchitecture.domain.factory

import com.example.cleanarchitecture.entity.Movie

fun createMovie() = Movie(
    id = 718444,
    title = "Bill & Ted Face the Musi",
    releaseDate = 0,
    overview = "Yet to fulfill their rock and roll destiny, the now middle-aged best friends Bill and Ted set out on a new adventure when a visitor from the future warns them that only their song can save life as we know it. Along the way, they are helped by their daughters, a new batch of historical figures and a few music legends—to seek the song that will set their world right and bring harmony to the universe.",
    voteAverage = 8.0F,
    backdropPath = "/oazPqs1z78LcIOFslbKtJLGlueo.jpg",
    posterPath = "/4V2nTPfeB59TcqJcUfQ9ziTi7VN.jpg"
)

fun createMovies() = listOf<Movie>(
    Movie(
        id = 718444,
        title = "Bill & Ted Face the Musi",
        releaseDate = 0,
        overview = "Yet to fulfill their rock and roll destiny, the now middle-aged best friends Bill and Ted set out on a new adventure when a visitor from the future warns them that only their song can save life as we know it. Along the way, they are helped by their daughters, a new batch of historical figures and a few music legends—to seek the song that will set their world right and bring harmony to the universe.",
        voteAverage = 8.0F,
        backdropPath = "/oazPqs1z78LcIOFslbKtJLGlueo.jpg",
        posterPath = "/4V2nTPfeB59TcqJcUfQ9ziTi7VN.jpg"
    ),
    Movie(
        id = 385103,
        title = "After We Collided",
        releaseDate = 0,
        overview = "Tessa finds herself struggling with her complicated relationship with Hardin; she faces a dilemma that could change their lives forever.",
        voteAverage = 7.0F,
        backdropPath = "/dZJJDmiwp0W1NE74SY5WV00v0Ec.jpg",
        posterPath = "/tM4hht0LdY06UbuxGR4LjK6adCD.jpg"
    )
)
