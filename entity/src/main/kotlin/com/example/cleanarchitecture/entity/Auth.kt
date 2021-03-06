package com.example.cleanarchitecture.entity

data class Auth(
    val userId: String,
    val username: String? = null,
    val email: String? = null,
    val token: String? = null
) : Model()
