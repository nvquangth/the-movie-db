package com.example.cleanarchitecture.domain.usecase.user

import com.example.cleanarchitecture.domain.repository.UserRepository
import com.example.cleanarchitecture.domain.usecase.UseCase
import com.example.cleanarchitecture.entity.User
import com.example.cleanarchitecture.entity.exception.CleanException
import com.example.cleanarchitecture.entity.exception.CleanExceptionType
import javax.inject.Inject

class SearchUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<SearchUserUseCase.Params, List<User>>() {

    data class Params(val q: String, val fromServer: Boolean)

    override suspend fun execute(param: Params?): List<User> = param?.let {
        userRepository.searchUsers(it.fromServer, it.q)
    } ?: throw CleanException(CleanExceptionType.PARAM_NULL)
}
