package com.example.cleanarchitecture.data.repository

import com.example.cleanarchitecture.data.extension.throwCleanException
import com.example.cleanarchitecture.data.model.UserEntityMapper
import com.example.cleanarchitecture.data.remote.UserApi
import com.example.cleanarchitecture.domain.repository.UserRepository
import com.example.cleanarchitecture.entity.User
import com.example.cleanarchitecture.entity.exception.CleanException
import com.example.cleanarchitecture.entity.exception.CleanExceptionType
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi,
    private val userEntityMapper: UserEntityMapper
) : UserRepository {
    override suspend fun searchUsers(fromServer: Boolean, q: String): List<User> {
        try {
            val response = userApi.searchUser(q)

            return response.items?.map {
                userEntityMapper.mapToDomain(it)
            } ?: emptyList()
        } catch (e: Exception) {
            e.throwCleanException()
        }
        throw CleanException(CleanExceptionType.DATA_NULL_OR_EMPTY)
    }

    override suspend fun getUser(fromServer: Boolean, username: String): User {
        try {
            return userApi.getUser(username).let { userEntityMapper.mapToDomain(it) }
        } catch (e: Exception) {
            e.throwCleanException()
        }
        throw CleanException(CleanExceptionType.DATA_NULL_OR_EMPTY)
    }
}
