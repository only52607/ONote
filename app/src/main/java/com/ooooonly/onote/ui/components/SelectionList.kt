package com.ooooonly.onote.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

sealed class SelectionItem(
    @StringRes val labelResId: Int,
    @StringRes val contentDescriptionResId: Int,
    val color: Color? = null,
) {
    class ResourceIcon(
        @StringRes labelResId: Int,
        @StringRes contentDescriptionResId: Int,
        @DrawableRes val iconResId: Int,
        color: Color? = null,
    ) : SelectionItem(labelResId, contentDescriptionResId, color)

    class ImageVectorIcon(
        @StringRes labelResId: Int,
        @StringRes contentDescriptionResId: Int,
        val iconImageVector: ImageVector,
        color: Color? = null
    ) : SelectionItem(labelResId, contentDescriptionResId, color)
}

@Composable
fun SelectionIcon(item: SelectionItem) {
    val painter = when (item) {
        is SelectionItem.ResourceIcon -> painterResource(item.iconResId)
        is SelectionItem.ImageVectorIcon -> rememberVectorPainter(item.iconImageVector)
    }
    Icon(
        painter = painter,
        contentDescription = stringResource(item.contentDescriptionResId),
        tint = item.color ?: LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SelectionList(
    modifier: Modifier = Modifier,
    items: List<SelectionItem>,
    onClick: (SelectionItem) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(items) {
            ListItem(
                icon = { SelectionIcon(it) },
                text = { Text(stringResource(it.labelResId), color = it.color ?: Color.Unspecified) },
                modifier = Modifier.clickable { onClick(it) }
            )
        }
    }
}

@Composable
fun rememberSelectionItem(
    @StringRes labelResId: Int,
    @StringRes contentDescriptionResId: Int,
    @DrawableRes iconResId: Int,
    color: Color? = null,
) = remember {
    SelectionItem.ResourceIcon(labelResId, contentDescriptionResId, iconResId, color)
}