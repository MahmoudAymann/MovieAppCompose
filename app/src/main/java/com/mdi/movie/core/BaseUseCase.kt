package com.mdi.movie.core

interface BaseUseCaseParam<in Params, out T> {
    suspend operator fun invoke(params: Params): T
}

