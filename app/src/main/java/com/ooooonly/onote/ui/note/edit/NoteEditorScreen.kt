package com.ooooonly.onote.ui.note.edit

import android.widget.EditText
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.ooooonly.onote.R
import com.ooooonly.onote.model.NoteViewModel
import com.ooooonly.onote.ui.components.*
import com.ooooonly.onote.utils.rememberAndroidViewContainer
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NoteEditorScreen(
    noteViewModel: NoteViewModel,
    onFinished: () -> Unit,
    onBackPressed: () -> Unit,
    editable: Boolean = false,
) {
    var content by remember { mutableStateOf("") }
    var saving by remember { mutableStateOf(false) }
    val noteState = noteViewModel.editingNoteState ?: return
    val coroutineScope = rememberCoroutineScope()
    var currentEditable by remember { mutableStateOf(editable) }
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
                    AnimatedVisibility(currentEditable) {
                        Row {
                            EditorActionIcons()
                        }
                    }
                    EditableSwitchIconButton(
                        editable = currentEditable,
                        onEditableChange = { currentEditable = it }
                    )
                    IconButton(onClick = save) {
                        Icon(Icons.Filled.Done, contentDescription = null)
                    }
                },
                title = { Text(stringResource(R.string.note_editor_title)) }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface)
        ) {
            val editTextContainer = rememberAndroidViewContainer<EditText>()
            ContentPadding {
                Crossfade(currentEditable) { value ->
                    if (value) {
                        Column(modifier = Modifier.fillMaxSize().navigationBarsWithImePadding()) {
                            MarkwonEditorComposable(
                                modifier = Modifier.weight(1f).fillMaxWidth(),
                                content = content,
                                onContentChanged = { content = it },
                                viewContainer = editTextContainer
                            )
                            Row(
                                modifier = Modifier.fillMaxWidth()
                                    .horizontalScroll(rememberScrollState())
                            ) {
                                EditorOperations(
                                    viewContainer = editTextContainer
                                )
                            }
                        }
                    } else {
                        MarkwonComposable(
                            modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(rememberScrollState()),
                            content = content
                        )
                    }
                }
            }
        }
    }
    if (saving) {
        ProgressDialog(
            message = stringResource(R.string.note_editor_saving)
        )
    }
}

@Composable
private fun EditableSwitchIconButton(
    editable: Boolean,
    onEditableChange: (Boolean) -> Unit,
) {
    IconButton(onClick = { onEditableChange(!editable) }) {
        Crossfade(editable) {
            if (!it) {
                Icon(Icons.Filled.Edit, contentDescription = null)
            } else {
                Icon(Icons.Filled.Visibility, contentDescription = null)
            }
        }
    }
}

private fun String.getTitle() = substring(0, kotlin.math.min(10, length))
private fun String.getBrief() = substring(0, kotlin.math.min(25, length))
