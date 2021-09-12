package com.ooooonly.onote.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object PaddingDefaults {
    val all = 16.dp
}

@Composable
fun Padding(all: Dp = PaddingDefaults.all, content: @Composable BoxScope.() -> Unit) {
    Box(modifier = Modifier.padding(all), content = content)
}