package com.example.cleanarchitecture.domain.usecase.auth

import com.example.cleanarchitecture.domain.repository.AuthRepository
import com.example.cleanarchitecture.domain.usecase.UseCase
import com.example.cleanarchitecture.entity.Auth

class SaveLoginUseCase(
    private val authRepository: AuthRepository
) : UseCase<SaveLoginUseCase.Params, Unit>() {
    data class Params(val auth: Auth)

    override suspend fun execute(param: Params?) {
        TODO("Not yet implemented")
    }
}
