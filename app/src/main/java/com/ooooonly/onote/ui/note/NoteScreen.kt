package com.ooooonly.onote.ui.note

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DragIndicator
import androidx.compose.material.icons.filled.Segment
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ooooonly.onote.R
import com.ooooonly.onote.model.NoteViewModel
import com.ooooonly.onote.ui.components.TitleText

@Composable
fun NoteScreen(
    noteViewModel: NoteViewModel,
    navigationIcon: @Composable () -> Unit,
    toEditor: () -> Unit
) {
    var listStyle by remember { mutableStateOf(NoteListStyle.Column) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { TitleText(R.string.note_title) },
                backgroundColor = MaterialTheme.colors.surface,
                navigationIcon = navigationIcon,
                actions = {
                    IconButton(onClick = {
                        listStyle = if (listStyle == NoteListStyle.Column) NoteListStyle.Flow
                        else NoteListStyle.Column
                    }) {
                        Crossfade(targetState = listStyle) {
                            when(it) {
                                NoteListStyle.Column -> Icon(Icons.Filled.Segment, contentDescription = null)
                                NoteListStyle.Flow -> Icon(Icons.Filled.DragIndicator, contentDescription = null)
                            }
                        }
                    }
                }
            )
        }
    ) {
        NoteList(
            modifier = Modifier.background(Color.Transparent),
            notes = noteViewModel.notes,
            onNoteItemEvent = { event ->
                when(event) {
                    is NoteItemEvent.OnClick -> {
                        noteViewModel.setEditingNoteState(event.noteState)
                        toEditor()
                    }
                }
            },
            style = listStyle
        )
    }
}