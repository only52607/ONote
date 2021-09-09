package com.ooooonly.onote.model

import androidx.lifecycle.ViewModel
import com.ooooonly.onote.data.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    todoRepository: TodoRepository
): ViewModel() {

}