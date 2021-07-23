package com.example.cleanarchitecture.domain.usecase.auth

import com.example.cleanarchitecture.domain.repository.AuthRepository
import com.example.cleanarchitecture.domain.usecase.UseCase
import com.example.cleanarchitecture.entity.exception.CleanException
import com.example.cleanarchitecture.entity.exception.CleanExceptionType
import javax.inject.Inject

open class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : UseCase<LoginUseCase.Params, Boolean>() {

    data class Params(
        val username: String,
        val password: String,
        val rememberLogin: Boolean = false
    )

    override suspend fun execute(param: Params?): Boolean = param?.let {

        authRepository.login(it.username, it.password)?.let { auth ->
            if (param.rememberLogin) {
                authRepository.saveAuth(auth)
            }
            true
        } ?: throw CleanException(CleanExceptionType.UNAUTHORIZED)
    } ?: throw CleanException(CleanExceptionType.PARAM_NULL)
}
