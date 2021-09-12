package com.ooooonly.onote.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object SpaceDefaults {
    val gap: Dp = 16.dp
}

/**
 * 为元素提供等距间隙
 */
@Composable
inline fun SpaceRow(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
    gap: Dp = SpaceDefaults.gap,
    itemGenerator: SpaceRowScope.() -> Unit
) {
    val items: MutableList<@Composable RowScope.() -> Unit> = mutableListOf()
    val scope = remember {
        object: SpaceRowScope {
            override fun item(content: @Composable RowScope.() -> Unit) {
                items.add(content)
            }
        }
    }
    items.clear()
    scope.itemGenerator()
    Row(modifier, horizontalArrangement, verticalAlignment) {
        var first = true
        for (item in items) {
            if (!first) {
                Spacer(modifier = Modifier.width(gap))
            } else {
                first = false
            }
            item()
        }
    }
}

interface SpaceRowScope {
    fun item(content: @Composable RowScope.() -> Unit)
}