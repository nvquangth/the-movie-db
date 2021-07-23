package com.example.cleanarchitecture.data.pref

import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.cleanarchitecture.data.extension.throwCleanException
import com.example.cleanarchitecture.data.model.AuthEntity
import com.google.gson.Gson

class AppPref(
    private val pref: SharedPreferences,
    private val gson: Gson
) : PrefHelper {

    companion object {
        const val PREF_LOGIN = "pref_login"
        const val PREF_AUTH = "pref_auth"
    }

    override fun remove(key: String) {
        pref.edit {
            remove(key)
        }
    }

    override fun clear() {
        pref.edit {
            clear()
        }
    }

    override suspend fun isLogin(): Boolean = pref.getBoolean(PREF_LOGIN, false)

    override suspend fun setLogin(login: Boolean) {
        pref.edit {
            putBoolean(PREF_LOGIN, login)
        }
    }

    override suspend fun setAuth(auth: AuthEntity) {
        val authStr = gson.toJson(auth)

        pref.edit {
            putString(PREF_AUTH, authStr)
        }
    }

    override suspend fun getAuth(): AuthEntity? {
        val authStr = pref.getString(PREF_AUTH, "")

        if (authStr.isNullOrEmpty()) return null

        return runCatching {
            gson.fromJson(authStr, AuthEntity::class.java)
        }.getOrElse {
            it.throwCleanException()
            null
        }
    }

    override suspend fun clearAuth() {
        remove(PREF_AUTH)
    }
}
