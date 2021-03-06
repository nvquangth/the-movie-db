package com.example.cleanarchitecture.domain.repository

import com.example.cleanarchitecture.entity.Auth

interface AuthRepository : Repository {

    suspend fun login(username: String, password: String): Auth?

    suspend fun signUp(username: String, password: String, email: String): Auth?

    suspend fun logout(): Boolean

    suspend fun saveAuth(auth: Auth)

    suspend fun clearAuth()

    suspend fun isLogin(): Boolean
}
