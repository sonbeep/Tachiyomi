package com.example.tachiyomi.model.app_util

data class Failure(
    val errorType: ErrorType? = null,
    // error message
    override val message: String? = null,
    // error code from server
    val errorCode: Int? = null,
) : Throwable()
