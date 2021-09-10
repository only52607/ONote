package com.ooooonly.onote.data.dao

import androidx.room.*
import com.ooooonly.onote.model.entity.Todo

@Dao
interface TodoDao {
    @Query("SELECT * FROM note_package")
    suspend fun loadAllTodos(): Array<Todo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTodo(vararg todos: Todo): Long

    @Delete
    suspend fun deleteTodo(vararg todos: Todo)
}