package com.bt.presentation.base.model

import com.example.cleanarchitecture.entity.exception.CleanExceptionType

class DialogExceptionItem(
    val type: CleanExceptionType? = null,
    val message: String? = null,
    val title: String? = null,
    val positiveButton: String? = null,
    val negativeButton: String? = null
) : ExceptionItem
