package com.ooooonly.onote.ui.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.google.accompanist.flowlayout.FlowRow
import com.ooooonly.onote.R
import com.ooooonly.onote.model.NoteViewModel
import com.ooooonly.onote.ui.components.ContentPadding
import com.ooooonly.onote.ui.components.ContentSpacer

@Composable
fun AppDrawer(
    noteViewModel: NoteViewModel,
    onCloseDrawer: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.background)) {
        ContentSpacer(gap = headerSpace)
        ContentPadding {
            Text(
                stringResource(R.string.drawer_title),
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold
            )
        }
        ContentSpacer()
        FlowRow {
            noteViewModel.notePackages.forEach {
                NotePackageCard(
                    notePackageState = it,
                    onClick = {
                        noteViewModel.setCurrentNotePackage(it)
                        onCloseDrawer()
                    },
                    selected = it == noteViewModel.currentNotePackage
                )
            }
        }
    }
}

private val headerSpace = 56.dp