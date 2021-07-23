package com.example.cleanarchitecture.domain.usecase.user

import com.example.cleanarchitecture.domain.repository.UserRepository
import com.example.cleanarchitecture.domain.usecase.UseCase
import com.example.cleanarchitecture.entity.User
import com.example.cleanarchitecture.entity.exception.CleanException
import com.example.cleanarchitecture.entity.exception.CleanExceptionType
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<GetUserUseCase.Params, User>() {

    data class Params(val username: String, val fromServer: Boolean)

    override suspend fun execute(param: Params?): User = param?.let {
        userRepository.getUser(it.fromServer, it.username)
    } ?: throw CleanException(CleanExceptionType.PARAM_NULL)
}
