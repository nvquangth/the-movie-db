package com.example.cleanarchitecture.data.remote

import com.example.cleanarchitecture.data.model.UserEntity
import com.example.data.model.SearchUserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApi {

    @GET("/users")
    suspend fun searchUser(@Query("q") q: String): SearchUserResponse

    @GET("/users/{username}")
    suspend fun getUser(@Path("username") username: String): UserEntity
}
