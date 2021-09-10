package com.ooooonly.onote.data.repsitory.impl

import android.os.Build
import androidx.annotation.RequiresApi
import com.ooooonly.onote.data.dao.TodoDao
import com.ooooonly.onote.data.repsitory.TodoRepository
import com.ooooonly.onote.model.entity.Todo
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
class TodoRepositoryImpl @Inject constructor(
    private val todoDao: TodoDao
): TodoRepository {
    override suspend fun listTodos(): Array<Todo> {
        return todoDao.loadAllTodos()
    }

    override suspend fun removeTodo(todo: Todo) {
        todoDao.deleteTodo(todo)
    }

    override suspend fun saveTodo(todo: Todo) {
        todo.updateModifyTime()
        todo.id = todoDao.saveTodo(todo)
    }
}