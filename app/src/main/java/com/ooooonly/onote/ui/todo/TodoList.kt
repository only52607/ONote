package com.ooooonly.onote.ui.todo

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import com.ooooonly.onote.model.TodoState
import com.ooooonly.onote.R
import com.ooooonly.onote.ui.components.ListItemPadding

@Composable
fun TodoList(
    modifier: Modifier = Modifier,
    doneTodos: List<TodoState>,
    unDoneTodos: List<TodoState>,
    onTodoItemEvent: (TodoItemEvent) -> Unit,
) {
    LazyColumn(modifier = modifier) {
        items(unDoneTodos) { todo ->
            TodoListItem(
                todoState = todo,
                onTodoItemEvent = onTodoItemEvent,
                coroutineScope = rememberCoroutineScope()
            )
        }
        if (doneTodos.isNotEmpty()) {
            item {
                CompositionLocalProvider(
                    LocalContentAlpha provides ContentAlpha.medium,
                    LocalTextStyle provides MaterialTheme.typography.caption
                ) {
                    ListItemPadding {
                        Text(stringResource(R.string.todo_done_label))
                    }
                }
            }
            items(doneTodos) { todo ->
                CompositionLocalProvider(
                    LocalContentAlpha provides ContentAlpha.disabled,
                    LocalTextStyle provides TextStyle.Default.copy(textDecoration = TextDecoration.LineThrough)
                ) {
                    TodoListItem(
                        todoState = todo,
                        onTodoItemEvent = onTodoItemEvent,
                        coroutineScope = rememberCoroutineScope()
                    )
                }
            }
        }
    }
}