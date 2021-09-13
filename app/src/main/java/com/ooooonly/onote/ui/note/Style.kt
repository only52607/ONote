package com.ooooonly.onote.ui.note

import androidx.compose.runtime.compositionLocalOf

data class NoteItemStyle(
    val showPackageName: Boolean = false
)

val LocalNoteItemStyle = compositionLocalOf { NoteItemStyle() }