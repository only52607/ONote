package com.ooooonly.onote.ui.note

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ooooonly.onote.model.NoteState
import com.ooooonly.onote.ui.components.Padding
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
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .clickable {
                onNoteItemEvent(NoteItemEvent.OnClick(noteState))
            }
    ) {
        Padding {
            Text(noteState.brief ?: "")
        }
    }
}

@Composable
fun NoteFlowListItem(
    noteState: NoteState,
    onNoteItemEvent: (NoteItemEvent) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(.4f)
            .clickable {
                onNoteItemEvent(NoteItemEvent.OnClick(noteState))
            }
    ) {
        Padding {
            Text(noteState.brief ?: "")
        }
    }
}