package com.ooooonly.onote.ui.todo

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ooooonly.onote.R
import com.ooooonly.onote.model.TodoViewModel
import com.ooooonly.onote.ui.components.TitleText

@Composable
fun TodoScreen(
    todoViewModel: TodoViewModel,
    navigationIcon: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { TitleText(R.string.todo_title) },
                backgroundColor = MaterialTheme.colors.surface,
                navigationIcon = navigationIcon,
            )
        }
    ) {
        TodoList(
            modifier = Modifier.padding(16.dp),
            todos = todoViewModel.todos,
            onTodoItemEvent = { event ->

            }
        )
    }
}