package com.ooooonly.onote.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

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
fun SelectionDialog(
    modifier: Modifier = Modifier,
    properties: DialogProperties = DialogProperties(),
    items: List<SelectionItem>,
    onDismissRequest: () -> Unit,
    onSelect: (SelectionItem) -> Unit,
) {
    Dialog (
        onDismissRequest = onDismissRequest,
        properties = properties
    ) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colors.surface,
            contentColor = contentColorFor(MaterialTheme.colors.surface),
        ) {
            SelectionList(modifier = modifier, items = items, onSelect)
        }
//        Box(modifier
//            .fillMaxSize()
//            .padding(bottom = 16.dp)
//            .clickable { onDismissRequest() }
//        ) {
//            Surface(
//                modifier = Modifier.align(Alignment.BottomCenter),
//                shape = MaterialTheme.shapes.medium,
//                color = MaterialTheme.colors.surface,
//                contentColor = contentColorFor(MaterialTheme.colors.surface),
//            ) {
//                SelectionList(modifier = modifier, items = items, onSelect)
//            }
//        }
    }
}

/**
 * Builders
 */

class SelectionsBuilder(
    val list: MutableList<SelectionItem>
) {
    fun item(@StringRes labelResId: Int,
             @DrawableRes iconResId: Int,
             @StringRes contentDescriptionResId: Int = labelResId,
             color: Color? = null
    ) {
        list.add(SelectionItem.ResourceIcon(
            labelResId = labelResId,
            contentDescriptionResId = contentDescriptionResId,
            iconResId = iconResId,
            color = color
        ))
    }

    fun item(@StringRes labelResId: Int,
             iconImageVector: ImageVector,
             @StringRes contentDescriptionResId: Int = labelResId,
             color: Color? = null
    ) {
        list.add(SelectionItem.ImageVectorIcon(
            labelResId = labelResId,
            contentDescriptionResId = contentDescriptionResId,
            iconImageVector = iconImageVector,
            color = color
        ))
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

@Composable
fun rememberSelections(build: SelectionsBuilder.() -> Unit): List<SelectionItem> = remember(build) {
    val list = mutableListOf<SelectionItem>()
    SelectionsBuilder(list).build()
    list
}