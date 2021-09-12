package com.ooooonly.onote.ui.note.edit

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ooooonly.onote.R
import com.ooooonly.onote.model.NoteViewModel
import com.ooooonly.onote.ui.components.ProgressDialog
import com.ooooonly.onote.ui.components.TitleText
import kotlinx.coroutines.launch

@Composable
fun NoteEditorScreen(
    noteViewModel: NoteViewModel,
    onFinished: () -> Unit,
    onBackPressed: () -> Unit
) {
    var content by remember { mutableStateOf("") }
    var saving by remember { mutableStateOf(false) }
    val noteState = noteViewModel.editingNoteState ?: return
    val coroutineScope = rememberCoroutineScope()
    val save: () -> Unit = {
        coroutineScope.launch {
            saving = true
            noteState
                .apply {
                    title = content.getTitle()
                    brief = content.getBrief()
                }
                .also { it.storeContent(content) }
                .save()
            saving = false
            onFinished()
        }
    }
    LaunchedEffect(noteState) {
        content = noteState.loadContent() ?: ""
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
                    IconButton(onClick = save) {
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
    if (saving) {
        ProgressDialog(
            message = stringResource(R.string.note_editor_saving)
        )
    }
}

private fun String.getTitle() = substring(0, kotlin.math.min(10, length))
private fun String.getBrief() = substring(0, kotlin.math.min(25, length))
