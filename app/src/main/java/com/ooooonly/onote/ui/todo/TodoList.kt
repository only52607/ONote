package com.ooooonly.onote.ui.todo

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ooooonly.onote.model.TodoState


@Composable
fun TodoList(
    modifier: Modifier = Modifier,
    todos: List<TodoState>,
    onTodoItemEvent: (TodoItemEvent) -> Unit,
) {
    LazyColumn(modifier = modifier) {
        items(todos) { todo ->
            TodoListItem(todoState = todo, onTodoItemEvent)
        }
    }
}