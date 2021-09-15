package com.ooooonly.onote.ui.drawer

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import com.ooooonly.onote.R
import com.ooooonly.onote.model.NotePackageState
import com.ooooonly.onote.model.NoteViewModel
import com.ooooonly.onote.model.entity.NotePackage
import com.ooooonly.onote.ui.components.ContentHorizontalPadding
import com.ooooonly.onote.ui.components.ContentSpacer
import com.ooooonly.onote.ui.components.InputDialogHost
import com.ooooonly.onote.ui.components.rememberInputDialogState
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppDrawer(
    noteViewModel: NoteViewModel,
    onCloseDrawer: () -> Unit
) {
    val createNotePackageDialogState = rememberInputDialogState()
    val coroutineScope = rememberCoroutineScope()
    Column(modifier = Modifier.fillMaxSize()) {
        ContentSpacer(gap = headerSpace)
        ContentHorizontalPadding {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    stringResource(R.string.drawer_title),
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold
                )
                val createNotePackageDialogTitle = stringResource(R.string.note_note_package_create_title)
                IconButton(onClick = {
                    coroutineScope.launch {
                        createNotePackageDialogState.requireForInputOrNull(
                            title = createNotePackageDialogTitle
                        )?.let {
                            noteViewModel.createNotePackage(NotePackage(name = it))
                        }
                    }
                }) {
                    Icon(Icons.Filled.Add, contentDescription = null)
                }
            }
        }
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
    InputDialogHost(createNotePackageDialogState)
}

private val headerSpace = 56.dp