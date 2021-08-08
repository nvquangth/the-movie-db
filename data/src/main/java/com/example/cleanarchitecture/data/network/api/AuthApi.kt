package com.example.cleanarchitecture.data.network.api

import com.example.cleanarchitecture.data.network.response.AuthResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {

    @FormUrlEncoded
    @POST("/api/v1/auth/login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): AuthResponse

    @FormUrlEncoded
    @POST
    suspend fun signUp(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("email") email: String
    ): AuthResponse
}
