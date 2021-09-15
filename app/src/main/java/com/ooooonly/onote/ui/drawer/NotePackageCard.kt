package com.ooooonly.onote.ui.drawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ooooonly.onote.R
import com.ooooonly.onote.model.NotePackageState
import com.ooooonly.onote.ui.components.ContentPadding
import com.ooooonly.onote.ui.components.listPadding

@Composable
fun NotePackageCard(
    notePackageState: NotePackageState,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Box(modifier = Modifier
        .fillMaxWidth(.5f)) {
        ContentPadding {
            Card(
                shape = notePackageShape,
            ) {
                Box(
                    modifier = Modifier
                        .height(200.dp)
                        .clickable(onClick = onClick)
                ) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        painter = painterResource(R.drawable.bg_note_package),
                        contentDescription = null
                    )
                    Row {
                        Box(modifier = Modifier.fillMaxHeight().width(16.dp).background(MaterialTheme.colors.onSurface.copy(.3f)))
                        Box(
                            modifier = Modifier.fillMaxHeight().weight(1f),
                        ) {
                            Text(
                                notePackageState.name,
                                style = MaterialTheme.typography.subtitle1,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colors.onSurface.copy(.3f),
                                modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 5.dp)
                            )
                        }
                    }
                }
            }
        }
    }

}

val notePackageShape = RoundedCornerShape(
    topStart = 4.dp,
    topEnd = 24.dp,
    bottomEnd = 24.dp,
    bottomStart = 4.dp
)