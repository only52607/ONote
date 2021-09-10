package com.ooooonly.onote.data.repsitory

import com.ooooonly.onote.model.entity.Todo

interface TodoRepository {
    suspend fun listTodos(): Array<Todo>

    suspend fun removeTodo(todo: Todo)

    suspend fun saveTodo(todo: Todo)
}