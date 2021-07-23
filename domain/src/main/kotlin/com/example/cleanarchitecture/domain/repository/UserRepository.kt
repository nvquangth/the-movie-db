package com.example.cleanarchitecture.domain.repository

import com.example.cleanarchitecture.entity.User

interface UserRepository : Repository {

    suspend fun searchUsers(fromServer: Boolean, q: String): List<User>

    suspend fun getUser(fromServer: Boolean, username: String): User
}
