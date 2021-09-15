package com.ooooonly.onote.utils

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

open class DataRequestState<T> {
    private var continuation: Continuation<T>? by mutableStateOf(null)
    val active by derivedStateOf { continuation != null }

    fun resume(data: T) {
        synchronized(this) {
            if (continuation == null) error("State is not active")
            continuation!!.resume(data)
            continuation = null
        }
    }

    fun cancel() {
        synchronized(this) {
            if (continuation == null) error("State is not active")
            continuation!!.resumeWithException(DataRequestCancel)
            continuation = null
        }
    }

    suspend fun require(): T {
        return suspendCoroutine {
            continuation = it
        }
    }
}

object DataRequestCancel: Exception()