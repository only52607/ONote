package com.ooooonly.onote.data.dao

import androidx.room.*
import com.ooooonly.onote.model.entity.Todo

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo")
    suspend fun loadAllTodos(): Array<Todo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTodo(todo: Todo): Long

    @Delete
    suspend fun deleteTodo(vararg todos: Todo)
}