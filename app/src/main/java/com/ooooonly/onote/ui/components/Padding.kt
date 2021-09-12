package com.ooooonly.onote.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object ContentPaddingDefaults {
    val all = 16.dp
    val horizontal = 16.dp
    val vertical = 16.dp
}

@Composable
fun ContentPadding(all: Dp = ContentPaddingDefaults.all, content: @Composable BoxScope.() -> Unit) {
    Box(modifier = Modifier.padding(all), content = content)
}

@Composable
fun ContentPadding(
    horizontal: Dp = ContentPaddingDefaults.horizontal,
    vertical: Dp = ContentPaddingDefaults.vertical,
    content: @Composable BoxScope.() -> Unit
) {
    Box(modifier = Modifier.padding(horizontal, vertical), content = content)
}

@Composable
fun ContentPadding(
    paddingValues: PaddingValues,
    content: @Composable BoxScope.() -> Unit
) {
    Box(modifier = Modifier.padding(paddingValues), content = content)
}