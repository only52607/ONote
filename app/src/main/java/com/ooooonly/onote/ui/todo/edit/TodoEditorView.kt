package com.ooooonly.onote.ui.todo.edit

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ooooonly.onote.model.TodoState
import com.ooooonly.onote.model.TodoViewModel
import kotlinx.coroutines.launch

@Composable
fun TodoEditorView(
    todoViewModel: TodoViewModel,
    onDone: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = {
                coroutineScope.launch {
                    todoViewModel.editingTodo?.save()
                    onDone()
                }
            }) {
                Icon(Icons.Filled.Done, contentDescription = null)
            }
        }
        todoViewModel.editingTodo?.let { todoState ->
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalArrangement = Arrangement.End
            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = todoState.content,
                    onValueChange = { todoState.content = it }
                )
            }
        }
    }
}