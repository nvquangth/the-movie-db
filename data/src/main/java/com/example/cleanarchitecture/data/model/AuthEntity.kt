package com.example.cleanarchitecture.data.model

import com.example.cleanarchitecture.entity.Auth
import javax.inject.Inject

data class AuthEntity(
    val userId: String,
    val username: String? = null,
    val email: String? = null,
    val token: String? = null
) : ModelEntity

class AuthEntityMapper @Inject constructor() : EntityMapper<Auth, AuthEntity> {
    override fun mapToData(model: Auth): AuthEntity = AuthEntity(
        userId = model.userId,
        username = model.username,
        email = model.email,
        token = model.token
    )

    override fun mapToDomain(entity: AuthEntity): Auth = Auth(
        userId = entity.userId,
        username = entity.username,
        email = entity.email,
        token = entity.token
    )
}
