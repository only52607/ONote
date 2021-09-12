package com.ooooonly.onote.ui.todo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ooooonly.onote.model.TodoState
import com.ooooonly.onote.ui.components.ContentPadding
import com.ooooonly.onote.ui.components.SpaceRow
import com.ooooonly.onote.utils.UiEvent

sealed class TodoItemEvent(val todoState: TodoState) : UiEvent {
    class OnClick(todoState: TodoState) : TodoItemEvent(todoState)
}

@Composable
fun TodoListItem(
    todoState: TodoState,
    onTodoItemEvent: (TodoItemEvent) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                todoState.done = !todoState.done
                onTodoItemEvent(TodoItemEvent.OnClick(todoState))
            }
    ) {
        ContentPadding {
            SpaceRow {
                item {
                    Checkbox(
                        checked = todoState.done,
                        onCheckedChange = { todoState.done = !todoState.done }
                    )
                }
                item {
                    Text(todoState.content)
                }
            }
        }
    }
}