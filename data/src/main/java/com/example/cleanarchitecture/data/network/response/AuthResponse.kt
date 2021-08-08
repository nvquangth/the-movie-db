package com.example.cleanarchitecture.data.network.response

import com.example.cleanarchitecture.data.model.AuthEntity
import com.google.gson.annotations.SerializedName
import javax.inject.Inject

data class AuthResponse(
    @SerializedName("id")
    val userId: String,
    val username: String? = null,
    val email: String? = null,
    val token: String? = null
) : ResponseEntity

class AuthResponseEntityMapper @Inject constructor() : ResponseEntityMapper<AuthEntity, AuthResponse> {
    override fun mapToModelEntity(responseEntity: AuthResponse): AuthEntity = AuthEntity(
        userId = responseEntity.userId,
        username = responseEntity.username,
        email = responseEntity.email,
        token = responseEntity.token
    )

    override fun mapToResponseEntity(modelEntity: AuthEntity): AuthResponse = AuthResponse(
        userId = modelEntity.userId,
        username = modelEntity.username,
        email = modelEntity.email,
        token = modelEntity.token
    )
}
