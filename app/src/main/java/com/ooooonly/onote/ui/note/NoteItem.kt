package com.ooooonly.onote.ui.note

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ooooonly.onote.model.NoteState
import com.ooooonly.onote.ui.components.ContentPadding
import com.ooooonly.onote.ui.components.SpaceColumn
import com.ooooonly.onote.ui.components.SpaceColumnScope
import com.ooooonly.onote.ui.components.SpaceRow
import com.ooooonly.onote.utils.UiEvent
import com.ooooonly.onote.utils.listPadding
import com.ooooonly.onote.utils.prettyFormat

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
            .listPadding()
            .clickable {
                onNoteItemEvent(NoteItemEvent.OnClick(noteState))
            }
    ) {
        ContentPadding {
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
            .fillMaxWidth()
            .listPadding()
            .clickable {
                onNoteItemEvent(NoteItemEvent.OnClick(noteState))
            }
    ) {
        ContentPadding {
            SpaceColumn {
                item {
                    SpaceRow {
                        item {
                            Text(
                                noteState.entity.modifyTime.prettyFormat(),
                                style = MaterialTheme.typography.caption
                            )
                        }
                    }
                }
                item {
                    Text(noteState.brief ?: "")
                }
            }
        }
    }
}