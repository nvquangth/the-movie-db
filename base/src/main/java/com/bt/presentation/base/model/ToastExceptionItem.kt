package com.bt.presentation.base.model

import com.example.cleanarchitecture.entity.exception.CleanExceptionType

class ToastExceptionItem(
    val type: CleanExceptionType? = null,
    val message: String? = null
) : ExceptionItem
