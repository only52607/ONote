package com.ooooonly.onote.ui.note

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowColumn
import com.google.accompanist.flowlayout.FlowRow
import com.ooooonly.onote.model.NoteState
import com.ooooonly.onote.ui.components.EmptyView
import com.ooooonly.onote.ui.components.StaggeredVerticalGrid
import com.ooooonly.onote.utils.listPadding

@Composable
fun NoteList(
    modifier: Modifier = Modifier,
    notes: List<NoteState>,
    onNoteItemEvent: (NoteItemEvent) -> Unit,
    style: NoteListStyle
) {
    Box(modifier = Modifier.listPadding()) {
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
    LazyColumn(
        modifier = modifier
    ) {
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
    LazyColumn(
        modifier = modifier
    ) {
        item {
            StaggeredVerticalGrid(
                columns = 2
            ) {
                notes.forEach { NoteFlowListItem(noteState = it, onNoteItemEvent) }
            }
        }
    }
}