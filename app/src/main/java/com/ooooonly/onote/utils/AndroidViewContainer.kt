package com.ooooonly.onote.utils

import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

class AndroidViewContainer<T: View> {
    private var _view: T? = null

    fun setView(view: T) {
        _view = view
    }

    fun clearView() {
        _view = null
    }

    fun letView(block: (T) -> Unit) {
        _view?.let(block)
    }
}

@Composable
fun <T: View> rememberAndroidViewContainer() = remember {
    AndroidViewContainer<T>()
}