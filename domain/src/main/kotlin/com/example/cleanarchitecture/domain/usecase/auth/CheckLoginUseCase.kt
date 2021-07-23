package com.example.cleanarchitecture.domain.usecase.auth

import com.example.cleanarchitecture.domain.repository.AuthRepository
import com.example.cleanarchitecture.domain.usecase.UseCase
import javax.inject.Inject

class CheckLoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : UseCase<Unit, Boolean>() {

    override suspend fun execute(param: Unit?): Boolean {
        return authRepository.isLogin()
    }
}
