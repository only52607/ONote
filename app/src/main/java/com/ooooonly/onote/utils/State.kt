package com.ooooonly.onote.utils

import androidx.compose.runtime.MutableState

private fun <T> MutableState<T>.use(initialValue: T, endValue: T, block: () -> Unit) {
    value = initialValue
    block()
    value = endValue
}

private suspend fun <T> MutableState<T>.use(initialValue: T, endValue: T, block: suspend () -> Unit) {
    value = initialValue
    block()
    value = endValue
}

fun MutableState<Boolean>.use(
    initialValue: Boolean = true,
    endValue: Boolean = false,
    block: () -> Unit
) =
    use<Boolean>(initialValue, endValue, block)

suspend fun MutableState<Boolean>.use(
    initialValue: Boolean = true,
    endValue: Boolean = false,
    block: suspend () -> Unit
) =
    use<Boolean>(initialValue, endValue, block)