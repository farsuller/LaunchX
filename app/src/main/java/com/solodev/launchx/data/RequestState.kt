package com.solodev.launchx.data

sealed class RequestState<out T>{
    data class Success<out T>(val result: T) : RequestState<T>()
    data class Error(val message: String) : RequestState<Nothing>()
}