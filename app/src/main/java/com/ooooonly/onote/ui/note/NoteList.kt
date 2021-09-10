package com.ooooonly.onote.ui.note

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import com.google.accompanist.flowlayout.FlowColumn
import com.ooooonly.onote.model.NoteState
import com.ooooonly.onote.ui.components.EmptyView

@Composable
fun NoteList(
    modifier: Modifier = Modifier,
    notes: List<NoteState>,
    onNoteItemEvent: (NoteItemEvent) -> Unit,
    style: NoteListStyle
) {
    if(notes.isEmpty()) {
        EmptyView()
    } else {
        Crossfade(targetState = style) {
            when(it) {
                NoteListStyle.Column -> NoteColumnList(modifier, notes, onNoteItemEvent)
                NoteListStyle.Flow -> NoteFlowList(modifier, notes, onNoteItemEvent)
            }
        }
    }
}

enum class NoteListStyle {
    Column, Flow
}

@Composable
fun NoteColumnList(
    modifier: Modifier = Modifier,
    notes: List<NoteState>,
    onNoteItemEvent: (NoteItemEvent) -> Unit,
) {
    LazyColumn(modifier = modifier) {
        items(notes) { note ->
            NoteColumnListItem(noteState = note, onNoteItemEvent)
        }
    }
}

@Composable
fun NoteFlowList(
    modifier: Modifier = Modifier,
    notes: List<NoteState>,
    onNoteItemEvent: (NoteItemEvent) -> Unit,
) {
    FlowColumn(modifier = modifier) {
        notes.forEach { NoteFlowListItem(noteState = it, onNoteItemEvent) }
    }
}