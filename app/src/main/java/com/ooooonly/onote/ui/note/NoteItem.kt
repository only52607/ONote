package com.ooooonly.onote.ui.note

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import com.ooooonly.onote.model.NoteState
import com.ooooonly.onote.ui.components.*
import com.ooooonly.onote.utils.UiEvent
import com.ooooonly.onote.utils.prettyFormat

sealed class NoteItemEvent(val noteState: NoteState) : UiEvent {
    class OnClick(noteState: NoteState) : NoteItemEvent(noteState)
}

@Composable
fun NoteListItem(
    noteState: NoteState,
    onNoteItemEvent: (NoteItemEvent) -> Unit,
    showPackage: Boolean = LocalNoteItemStyle.current.showPackageName,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .listItemPadding()
    ) {
        Box(modifier = Modifier.clickable {
            onNoteItemEvent(NoteItemEvent.OnClick(noteState))
        }) {
            ContentPadding {
                NoteListItemContent(noteState, showPackage)
            }
        }
    }
}

@Composable
private fun NoteListItemContent(
    noteState: NoteState,
    showPackage: Boolean,
) {
    Column {
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            CompositionLocalProvider(LocalTextStyle provides MaterialTheme.typography.caption) {
                Row {
                    Text(noteState.entity.modifyTime.prettyFormat(),)
                    if (showPackage) {
                        ContentSpacer()
                        Text(noteState.packageState.name)
                    }
                }
            }
        }
        ContentSpacer()
        Text(noteState.brief ?: "")
    }
}