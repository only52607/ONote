package com.ooooonly.onote.ui.todo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.ooooonly.onote.model.TodoState
import com.ooooonly.onote.ui.components.ContentPadding
import com.ooooonly.onote.ui.components.ContentSpacer
import com.ooooonly.onote.ui.components.listItemPadding
import com.ooooonly.onote.utils.UiEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

sealed class TodoItemEvent(val todoState: TodoState) : UiEvent {
    class OnClick(todoState: TodoState) : TodoItemEvent(todoState)
}

@Composable
fun TodoListItem(
    todoState: TodoState,
    onTodoItemEvent: (TodoItemEvent) -> Unit,
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .listItemPadding()
    ) {
        Box(modifier = Modifier.clickable { onTodoItemEvent(TodoItemEvent.OnClick(todoState)) }) {
            ContentPadding {
                TodoListItemContent(todoState, coroutineScope)
            }
        }
    }
}

@Composable
private fun TodoListItemContent(
    todoState: TodoState,
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) {
    Row {
        RadioButton(
            selected = todoState.done,
            onClick = {
                todoState.done = !todoState.done
                coroutineScope.launch {
                    todoState.save()
                }
            },
            colors = RadioButtonDefaults.colors(
                selectedColor = MaterialTheme.colors.onSurface.copy(
                    alpha = ContentAlpha.disabled
                )
            )
        )
        ContentSpacer()
        Text(todoState.content)
    }
}