package com.ooooonly.onote.ui.todo

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ooooonly.onote.R
import com.ooooonly.onote.model.TodoViewModel
import com.ooooonly.onote.ui.components.CenterTitleTopAppBar
import com.ooooonly.onote.utils.listPadding

@Composable
fun TodoScreen(
    todoViewModel: TodoViewModel,
    navigationIcon: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            CenterTitleTopAppBar(
                title = { Text(stringResource(R.string.todo_title)) },
                backgroundColor = MaterialTheme.colors.surface,
                navigationIcon = navigationIcon,
            )
        }
    ) {
        TodoList(
            modifier = Modifier.listPadding(),
            todos = todoViewModel.todos,
            onTodoItemEvent = { event ->

            }
        )
    }
}