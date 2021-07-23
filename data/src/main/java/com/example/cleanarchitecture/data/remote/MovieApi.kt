package com.example.cleanarchitecture.data.remote

import com.example.cleanarchitecture.data.model.MovieEntity
import com.example.cleanarchitecture.data.model.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("/3/search/movie")
    suspend fun searchMovies(
        @Query("query") q: String?,
        @Query("page") page: Int?
    ): MoviesResponse

    @GET("/3/movie/popular")
    suspend fun getPopularMovie(@Query("page") page: Int?): MoviesResponse

    @GET("/3/movie/{movie_id}")
    suspend fun getMovie(@Path("movie_id") movieId: Int): MovieEntity
}
