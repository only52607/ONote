package com.ooooonly.onote.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import org.intellij.lang.annotations.JdkConstants

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
    crossinline itemBuilder: SpaceRowScope.() -> Unit
) {
    val cache = remember { mutableListOf<@Composable RowScope.() -> Unit>() }
    val scope = remember {
        object: SpaceRowScope {
            override fun item(content: @Composable RowScope.() -> Unit) {
                cache.add(content)
            }
        }
    }
    val items: List<@Composable (RowScope.() -> Unit)> by derivedStateOf {
        cache.clear()
        scope.itemBuilder()
        cache
    }
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

@Composable
inline fun SpaceColumn(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    gap: Dp = SpaceDefaults.gap,
    crossinline itemBuilder: SpaceColumnScope.() -> Unit
) {
    val cache = remember { mutableListOf<@Composable ColumnScope.() -> Unit>() }
    val scope = remember {
        object: SpaceColumnScope {
            override fun item(content: @Composable ColumnScope.() -> Unit) {
                cache.add(content)
            }
        }
    }
    val items: List<@Composable (ColumnScope.() -> Unit)> by derivedStateOf {
        cache.clear()
        scope.itemBuilder()
        cache
    }
    Column (modifier, verticalArrangement, horizontalAlignment) {
        var first = true
        for (item in items) {
            if (!first) {
                Spacer(modifier = Modifier.height(gap))
            } else {
                first = false
            }
            item()
        }
    }
}

interface SpaceColumnScope {
    fun item(content: @Composable ColumnScope.() -> Unit)
}