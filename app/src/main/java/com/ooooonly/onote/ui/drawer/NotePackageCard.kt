package com.ooooonly.onote.ui.drawer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ooooonly.onote.model.NotePackageState
import com.ooooonly.onote.ui.components.listPadding

@Composable
fun NotePackageCard(
    notePackageState: NotePackageState,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(.5f)
            .listPadding()
    ) {
        Box(
            modifier = Modifier
                .height(500.dp)
                .clickable(onClick = onClick)
        ) {
            Text(notePackageState.name)
        }
    }
}