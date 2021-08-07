package com.example.cleanarchitecture.data

import com.example.cleanarchitecture.data.model.UserEntityMapper
import com.example.cleanarchitecture.data.remote.UserApi
import com.example.cleanarchitecture.domain.repository.UserRepository
import com.example.cleanarchitecture.entity.User
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi,
    private val euserEntityMapper: UserEntityMapper
) : UserRepository {
    override suspend fun searchUsers(fromServer: Boolean, q: String): List<User> = userApi.searchUser(q).items?.map {
        euserEntityMapper.mapToDomain(it)
    } ?: emptyList()

    override suspend fun getUser(fromServer: Boolean, username: String): User = userApi.getUser(username).let {
        euserEntityMapper.mapToDomain(it)
    }
}
