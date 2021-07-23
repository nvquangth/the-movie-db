package com.example.cleanarchitecture.domain.usecase.validation

import com.example.cleanarchitecture.domain.usecase.UseCase
import com.example.cleanarchitecture.entity.exception.CleanException
import com.example.cleanarchitecture.entity.exception.CleanExceptionType
import javax.inject.Inject

open class ValidateUsernameUseCase @Inject constructor() :
    UseCase<ValidateUsernameUseCase.Params, Boolean>() {

    data class Params(val username: String?)

    override suspend fun execute(param: Params?): Boolean {
        param?.let {
            val username = it.username

            if (username.isNullOrEmpty()) throw CleanException(CleanExceptionType.INPUT_NULL_OR_EMPTY)
            if (username.length < 5) throw CleanException(
                CleanExceptionType.INPUT_SHORT,
                "Username invalid"
            )

            return true
        }

        throw CleanException(CleanExceptionType.PARAM_NULL)
    }
}
