package com.ooooonly.onote.ui.note.edit

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Redo
import androidx.compose.material.icons.filled.Undo
import androidx.compose.runtime.Composable

@Composable
fun RowScope.EditorActionIcons(
    onUndoClick: () -> Unit = {},
    onRedoClick: () -> Unit = {}
) {
    IconButton(onClick = onUndoClick) {
        Icon(Icons.Filled.Undo, contentDescription = null)
    }
    IconButton(onClick = onRedoClick) {
        Icon(Icons.Filled.Redo, contentDescription = null)
    }
}