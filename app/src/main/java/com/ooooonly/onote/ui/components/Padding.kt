package com.ooooonly.onote.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val LocalListPadding = staticCompositionLocalOf { 4.dp }

val LocalListItemPadding = staticCompositionLocalOf { 4.dp }

@SuppressLint("UnnecessaryComposedModifier")
fun Modifier.listPadding() = composed { padding(LocalListPadding.current) }

@SuppressLint("UnnecessaryComposedModifier")
fun Modifier.listItemPadding() = padding(4.dp)

@Composable
fun ListItemPadding(content:@Composable BoxScope.() -> Unit) {
    Box(modifier = Modifier.listItemPadding(), content = content)
}

data class ContentPaddings(
    val all:Dp = 16.dp,
    val horizontal:Dp = 16.dp,
    val vertical:Dp = 16.dp,
)

val LocalContentPaddings = staticCompositionLocalOf { ContentPaddings() }

@Composable
fun ContentPadding(all: Dp = LocalContentPaddings.current.all, content: @Composable BoxScope.() -> Unit) {
    Box(modifier = Modifier.padding(all), content = content)
}

@Composable
fun ContentPadding(
    horizontal: Dp = LocalContentPaddings.current.horizontal,
    vertical: Dp = LocalContentPaddings.current.vertical,
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