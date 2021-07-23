package com.example.cleanarchitecture.domain.usecase

import com.example.cleanarchitecture.domain.repository.UserRepository
import com.example.cleanarchitecture.entity.User
import com.example.cleanarchitecture.entity.exception.ParamException
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<GetUserUseCase.Params, User>() {

    data class Params(val username: String, val fromServer: Boolean)

    override suspend fun execute(param: Params?): User {
        param?.let {
            return userRepository.getUser(it.fromServer, it.username)
        }
        throw ParamException()
    }
}
