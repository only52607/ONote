package com.ooooonly.onote.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.ooooonly.onote.R
import com.ooooonly.onote.utils.DataRequestCancel
import com.ooooonly.onote.utils.DataRequestState

@Composable
private fun EmptyDialog(
    onDismissRequest: () -> Unit,
    properties: DialogProperties = DialogProperties(),
    content: @Composable () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties
    ) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colors.surface,
            contentColor = contentColorFor(MaterialTheme.colors.surface),
        ) {
            content()
        }
    }
}

@Composable
fun InputDialog(
    onDismiss: () -> Unit = {},
    onConfirm: () -> Unit = {},
    content: String = "",
    onContentChange: (String) -> Unit,
    confirmText: String = stringResource(R.string.input_dialog_default_confirm),
    dismissText: String = stringResource(R.string.input_dialog_default_dismiss),
    titleText: String = "",
    additionalContent: @Composable ColumnScope.() -> Unit = {},
    showTextField: Boolean = true,
    textFieldMaxLine: Int = 1
) {
    EmptyDialog(
        onDismissRequest = onDismiss,
        content = {
            ContentPadding {
                Column {
                    Text(
                        text = titleText,
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Bold
                    )
                    additionalContent()
                    if (showTextField) {
                        TextField(
                            maxLines = textFieldMaxLine,
                            value = content,
                            onValueChange = onContentChange,
                            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent)
                        )
                    }
                    ContentSpacer()
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextButton(onClick = onDismiss) {
                            Text(dismissText)
                        }
                        TextButton(onClick = onConfirm) {
                            Text(confirmText)
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun InputDialogHost(
    inputDialogState: InputDialogState,
    content: @Composable InputDialogState.() -> Unit = @Composable { DefaultInputDialogHostContent() }
) {
    if (inputDialogState.active) {
        inputDialogState.content()
    }
}

@Composable
private fun InputDialogState.DefaultInputDialogHostContent() {
    InputDialog(
        onDismiss = { cancel() },
        onConfirm = { resume(currentText) },
        content = currentText,
        onContentChange = { currentText = it },
        titleText = currentTitleText ?: "",
        confirmText = currentConfirmText ?: stringResource(R.string.input_dialog_default_confirm),
        dismissText = currentDismissText ?: stringResource(R.string.input_dialog_default_dismiss),
    )
}

class InputDialogState : DataRequestState<String>() {
    var currentText by mutableStateOf("")
    var currentConfirmText: String? by mutableStateOf(null)
    var currentDismissText: String? by mutableStateOf(null)
    var currentTitleText: String? by mutableStateOf(null)

    suspend fun requireForInput(
        title: String? = null,
        confirmText: String? = null,
        dismissText: String? = null
    ): String {
        currentTitleText = title
        currentConfirmText = confirmText
        currentDismissText = dismissText
        return require()
    }

    suspend fun requireForInputOrNull(
        title: String? = null,
        confirmText: String? = null,
        dismissText: String? = null
    ): String? = try {
        requireForInput(title, confirmText, dismissText)
    } catch (e: DataRequestCancel) {
        null
    }
}

@Composable
fun rememberInputDialogState() = remember { InputDialogState() }