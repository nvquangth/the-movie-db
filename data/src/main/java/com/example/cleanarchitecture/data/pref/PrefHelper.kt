package com.example.cleanarchitecture.data.pref

import com.example.cleanarchitecture.data.model.AuthEntity

interface PrefHelper {

    /**
     * remove a preference by key
     */
    fun remove(key: String)

    /**
     * clear all preference
     */
    fun clear()

    suspend fun isLogin(): Boolean

    suspend fun setLogin(login: Boolean)

    suspend fun setAuth(auth: AuthEntity)

    suspend fun getAuth(): AuthEntity?

    suspend fun clearAuth()
}
