package com.example.cleanarchitecture.domain.usecase.auth

import com.example.cleanarchitecture.domain.repository.AuthRepository
import com.example.cleanarchitecture.domain.usecase.UseCase
import com.example.cleanarchitecture.entity.exception.CleanException
import com.example.cleanarchitecture.entity.exception.CleanExceptionType
import javax.inject.Inject

open class SignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : UseCase<SignUpUseCase.Params, Boolean>() {

    data class Params(val username: String, val password: String, val email: String)

    override suspend fun execute(param: Params?): Boolean = param?.let {

        authRepository.signUp(it.username, it.password, it.email)?.let {
            true
        } ?: throw CleanException(CleanExceptionType.UNAUTHORIZED)
    } ?: throw CleanException(CleanExceptionType.PARAM_NULL)
}
