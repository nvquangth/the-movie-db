package com.bt.presentation.base.model

import com.example.cleanarchitecture.entity.exception.CleanExceptionType

class SnackBarExceptionItem(
    val type: CleanExceptionType? = null,
    val message: String? = null
) : ExceptionItem
