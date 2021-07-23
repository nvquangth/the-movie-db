package com.example.cleanarchitecture.domain.extension

import com.example.cleanarchitecture.entity.exception.CleanException
import com.example.cleanarchitecture.entity.exception.CleanExceptionType

fun Throwable.throwCleanException() {
    when (this) {
        is CleanException -> throw this

        is NullPointerException -> {
        }

        else -> throw CleanException(CleanExceptionType.UNKNOWN, message)
    }
}
