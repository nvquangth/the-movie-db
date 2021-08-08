package com.example.cleanarchitecture.data.factory

import com.example.cleanarchitecture.data.model.MovieEntity
import com.example.cleanarchitecture.data.network.response.MoviesResponse

object MovieFactory {

    val MOVIE_ENTITY = MovieEntity(
        id = 718444,
        title = "Bill & Ted Face the Musi",
        releaseDate = "2020-09-15",
        overview = "Yet to fulfill their rock and roll destiny, the now middle-aged best friends Bill and Ted set out on a new adventure when a visitor from the future warns them that only their song can save life as we know it. Along the way, they are helped by their daughters, a new batch of historical figures and a few music legends—to seek the song that will set their world right and bring harmony to the universe.",
        voteAverage = 8.0F,
        backdropPath = "/oazPqs1z78LcIOFslbKtJLGlueo.jpg",
        posterPath = "/4V2nTPfeB59TcqJcUfQ9ziTi7VN.jpg"
    )

    val MOVIE_ENTITIES = listOf(
        MovieEntity(
            id = 718444,
            title = "Bill & Ted Face the Musi",
            releaseDate = "2020-09-15",
            overview = "Yet to fulfill their rock and roll destiny, the now middle-aged best friends Bill and Ted set out on a new adventure when a visitor from the future warns them that only their song can save life as we know it. Along the way, they are helped by their daughters, a new batch of historical figures and a few music legends—to seek the song that will set their world right and bring harmony to the universe.",
            voteAverage = 8.0F,
            backdropPath = "/oazPqs1z78LcIOFslbKtJLGlueo.jpg",
            posterPath = "/4V2nTPfeB59TcqJcUfQ9ziTi7VN.jpg"
        ),
        MovieEntity(
            id = 385103,
            title = "After We Collided",
            releaseDate = "2020-09-15",
            overview = "Tessa finds herself struggling with her complicated relationship with Hardin; she faces a dilemma that could change their lives forever.",
            voteAverage = 7.0F,
            backdropPath = "/dZJJDmiwp0W1NE74SY5WV00v0Ec.jpg",
            posterPath = "/tM4hht0LdY06UbuxGR4LjK6adCD.jpg"
        )
    )

    val MOVIES_RESPONSE = MoviesResponse(
        results = listOf(
            MovieEntity(
                id = 718444,
                title = "Bill & Ted Face the Musi",
                releaseDate = "2020-09-15",
                overview = "Yet to fulfill their rock and roll destiny, the now middle-aged best friends Bill and Ted set out on a new adventure when a visitor from the future warns them that only their song can save life as we know it. Along the way, they are helped by their daughters, a new batch of historical figures and a few music legends—to seek the song that will set their world right and bring harmony to the universe.",
                voteAverage = 8.0F,
                backdropPath = "/oazPqs1z78LcIOFslbKtJLGlueo.jpg",
                posterPath = "/4V2nTPfeB59TcqJcUfQ9ziTi7VN.jpg"
            ),
            MovieEntity(
                id = 385103,
                title = "After We Collided",
                releaseDate = "2020-09-15",
                overview = "Tessa finds herself struggling with her complicated relationship with Hardin; she faces a dilemma that could change their lives forever.",
                voteAverage = 7.0F,
                backdropPath = "/dZJJDmiwp0W1NE74SY5WV00v0Ec.jpg",
                posterPath = "/tM4hht0LdY06UbuxGR4LjK6adCD.jpg"
            )
        )
    )
}
