package com.example.cleanarchitecture.domain.usecase.validation

import com.example.cleanarchitecture.domain.usecase.UseCase
import com.example.cleanarchitecture.entity.exception.CleanException
import com.example.cleanarchitecture.entity.exception.CleanExceptionType
import javax.inject.Inject

open class ValidatePasswordUseCase @Inject constructor() :
    UseCase<ValidatePasswordUseCase.Params, Boolean>() {

    data class Params(val password: String?)

    override suspend fun execute(param: Params?): Boolean = param?.let {
        val password = it.password

        if (password.isNullOrEmpty()) throw CleanException(CleanExceptionType.INPUT_NULL_OR_EMPTY)
        if (password.length < 5) throw CleanException(
            CleanExceptionType.INPUT_SHORT,
            "Password invalid"
        )

        true
    } ?: throw CleanException(CleanExceptionType.PARAM_NULL)
}
