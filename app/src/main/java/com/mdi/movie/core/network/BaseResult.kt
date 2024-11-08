package com.mdi.movie.core.network

sealed class BaseResult<out T> {
    data class Success<out T>(val data: T) : BaseResult<T>()
    data class Error(val exception: Throwable, val message: String? = null) : BaseResult<Nothing>()
    data object Loading : BaseResult<Nothing>()
}