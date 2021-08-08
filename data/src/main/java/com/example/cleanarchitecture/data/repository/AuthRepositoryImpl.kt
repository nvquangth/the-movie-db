package com.example.cleanarchitecture.data.repository

import com.example.cleanarchitecture.data.model.AuthEntityMapper
import com.example.cleanarchitecture.data.network.api.AuthApi
import com.example.cleanarchitecture.data.network.response.AuthResponseEntityMapper
import com.example.cleanarchitecture.data.pref.PrefHelper
import com.example.cleanarchitecture.domain.repository.AuthRepository
import com.example.cleanarchitecture.entity.Auth
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi,
    private val pref: PrefHelper,
    private val authEntityMapper: AuthEntityMapper,
    private val authResponseEntityMapper: AuthResponseEntityMapper
) : AuthRepository {

    override suspend fun login(username: String, password: String): Auth = api.login(username, password)
        .let { authResponseEntityMapper.mapToModelEntity(it) }
        .let { authEntityMapper.mapToDomain(it) }

    override suspend fun signUp(username: String, password: String, email: String): Auth = api.signUp(username, password, email)
        .let { authResponseEntityMapper.mapToModelEntity(it) }
        .let { authEntityMapper.mapToDomain(it) }

    override suspend fun logout(): Boolean {
        // TODO update later
        return true
    }

    override suspend fun saveAuth(auth: Auth) {
        pref.setAuth(authEntityMapper.mapToData(auth))
    }

    override suspend fun clearAuth() {
        pref.clearAuth()
    }

    override suspend fun isLogin(): Boolean {
        return pref.getAuth() != null
    }
}
