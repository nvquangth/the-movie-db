package com.example.cleanarchitecture.domain.usecase.auth

import com.example.cleanarchitecture.domain.repository.AuthRepository
import com.example.cleanarchitecture.domain.usecase.UseCase
import com.example.cleanarchitecture.entity.exception.CleanException
import com.example.cleanarchitecture.entity.exception.CleanExceptionType
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : UseCase<Unit, Unit>() {
    override suspend fun execute(param: Unit?) {

        if (authRepository.logout()) {
            authRepository.clearAuth()
        } else {
            throw CleanException(CleanExceptionType.UNKNOWN)
        }
    }
}
