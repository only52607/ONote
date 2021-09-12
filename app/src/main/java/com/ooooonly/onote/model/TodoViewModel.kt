package com.ooooonly.onote.model

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ooooonly.onote.data.repsitory.TodoRepository
import com.ooooonly.onote.model.entity.Todo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val todoRepository: TodoRepository
): ViewModel() {
    
    private val _todos = mutableStateListOf<TodoState>()
    val todos: List<TodoState> = _todos

    private val _editingTodo = mutableStateOf<TodoState?>(null)
    val editingTodo: TodoState? by _editingTodo

    init {
        viewModelScope.launch { loadTodos() }
    }

    private suspend fun loadTodos() {
        _todos.clear()
        _todos.addAll(
            todoRepository.listTodos().map { TodoState(it, this) }
        )
    }

    fun removeTodo(todoState: TodoState) {
        _todos.remove(todoState)
        viewModelScope.launch {
            todoRepository.removeTodo(todoState.entity)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createEmptyEditingTodoState() {
        _editingTodo.value = TodoState(
            entity = Todo(),
            todoViewModel = this
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setEditingTodoState(todoState: TodoState) {
        _editingTodo.value = todoState
    }

    internal fun saveTodoState(todoState: TodoState) {
        viewModelScope.launch {
            todoRepository.saveTodo(todoState.entity)
            loadTodos()
        }
    }
}

class TodoState(
    override val entity: Todo,
    private val todoViewModel: TodoViewModel
) : RoomEntityState<Todo> {
    var content: String by mutableStateOf(entity.content)
    var done: Boolean by mutableStateOf(entity.done)
    var notifyCron: String? by mutableStateOf(entity.notifyCron)

    override suspend fun save() {
        entity.content = content
        entity.done = done
        entity.notifyCron = notifyCron
        todoViewModel.saveTodoState(this)
    }
}