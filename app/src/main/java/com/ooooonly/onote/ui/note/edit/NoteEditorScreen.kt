package com.ooooonly.onote.ui.note.edit

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import com.ooooonly.onote.model.NoteViewModel
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.ooooonly.onote.R
import com.ooooonly.onote.ui.components.TitleText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun NoteEditorScreen(
    noteViewModel: NoteViewModel,
    onFinished: () -> Unit,
    onBackPressed: () -> Unit
) {
    var content by remember { mutableStateOf("") }

    val noteState = noteViewModel.editingNoteState ?: return

    LaunchedEffect(noteState) {
        if (noteState.entity.file.exists()) {
            withContext(Dispatchers.IO) {
                content = noteState.entity.file.readText()
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.surface,
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    IconButton(onClick = {
                        LaunchedEffect(Unit) {
                            noteState.title = content.getTitle()
                            noteState.brief = content.getBrief()
                            noteState.save()
                            withContext(Dispatchers.IO) {
                                noteState.entity.file.writeText(content)
                            }
                        }
                        onFinished()
                    }) {
                        Icon(Icons.Filled.Done, contentDescription = null)
                    }
                },
                title = { TitleText(R.string.note_editor_title) }
            )
        }
    ) {
        TextField(
            modifier = Modifier.fillMaxSize(),
            value = content,
            onValueChange = { content = it }
        )
    }
}

private fun String.getTitle() = substring(0, kotlin.math.max(10, length))
private fun String.getBrief() = substring(0, kotlin.math.max(25, length))
