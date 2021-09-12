package com.ooooonly.onote.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ooooonly.onote.R

/**
 * 空白视图
 */
@Composable
fun EmptyView(
    modifier: Modifier = Modifier,
    @DrawableRes pictureResId: Int = R.drawable.bg_blank_1,
    @StringRes messageResId: Int = R.string.empty_default,
) {
    Box(modifier = Modifier.fillMaxSize().padding(30.dp).then(modifier), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(pictureResId), contentDescription = null)
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                stringResource(messageResId),
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Bold,
                color = contentColorFor(MaterialTheme.colors.surface).copy(alpha = .5f)
            )
        }
    }
}