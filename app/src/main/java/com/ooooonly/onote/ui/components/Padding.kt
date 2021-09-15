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
import androidx.compose.ui.unit.LayoutDirection
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

val LocalContentPaddingValue = staticCompositionLocalOf {
    PaddingValues(
        top = 16.dp,
        end = 16.dp,
        start = 16.dp,
        bottom = 16.dp,
    )
}

@Composable
fun ContentPadding(
    all: Dp,
    content: @Composable BoxScope.() -> Unit
) {
    Box(modifier = Modifier.padding(all), content = content)
}

@Composable
fun ContentPadding(
    horizontal: Dp,
    vertical: Dp,
    content: @Composable BoxScope.() -> Unit
) {
    Box(modifier = Modifier.padding(horizontal, vertical), content = content)
}

@Composable
fun ContentVerticalPadding(
    vertical: Dp = LocalContentPaddingValue.current.calculateTopPadding(),
    content: @Composable BoxScope.() -> Unit
) {
    Box(modifier = Modifier.padding(vertical = vertical, horizontal = 0.dp), content = content)
}

@Composable
fun ContentHorizontalPadding(
    horizontal: Dp = LocalContentPaddingValue.current.calculateLeftPadding(LayoutDirection.Ltr),
    content: @Composable BoxScope.() -> Unit
) {
    Box(modifier = Modifier.padding(vertical = 0.dp, horizontal = horizontal), content = content)
}

@Composable
fun ContentPadding(
    paddingValues: PaddingValues = LocalContentPaddingValue.current,
    content: @Composable BoxScope.() -> Unit
) {
    Box(modifier = Modifier.padding(paddingValues), content = content)
}