package com.ooooonly.onote.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign

@Composable
fun TitleText(
    @StringRes textRes: Int
) {
    Text(stringResource(textRes), modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
}