package com.ooooonly.onote.ui.note

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ooooonly.onote.model.NoteState
import com.ooooonly.onote.utils.UiEvent

sealed class NoteItemEvent(val noteState: NoteState) : UiEvent {
    class OnClick(noteState: NoteState) : NoteItemEvent(noteState)
}

@Composable
fun NoteColumnListItem(
    noteState: NoteState,
    onNoteItemEvent: (NoteItemEvent) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onNoteItemEvent(NoteItemEvent.OnClick(noteState))
            }
    ) {
        Text(noteState.brief ?: "")
    }
}

@Composable
fun NoteFlowListItem(
    noteState: NoteState,
    onNoteItemEvent: (NoteItemEvent) -> Unit,
) {
    Card(
        modifier = Modifier
            .width(200.dp)
            .clickable {
                onNoteItemEvent(NoteItemEvent.OnClick(noteState))
            }
    ) {
        Text(noteState.brief ?: "")
    }
}