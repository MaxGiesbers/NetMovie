package com.net.movie.Home.data.data_source

import java.lang.Exception

sealed class Resource<out T> {
    data class Success<out T>(val result: T): Resource<T>()
    data class Failure(val exception: Exception): Resource<Nothing>()
    object Loading:Resource<Nothing>()
}