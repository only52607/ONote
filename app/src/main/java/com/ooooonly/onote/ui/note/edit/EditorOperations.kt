package com.ooooonly.onote.ui.note.edit

import android.widget.EditText
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.ooooonly.onote.ui.components.ContentSpacer
import com.ooooonly.onote.utils.AndroidViewContainer

@Composable
fun RowScope.EditorOperations(
    viewContainer: AndroidViewContainer<EditText>
) {
    EditorOperationButton(
        onClick = viewContainer.makeFormatOperationEvent(SurroundedFormatOperation("**"))
    ) {
        Icon(Icons.Filled.FormatBold, contentDescription = null)
    }
    ContentSpacer()
    EditorOperationButton(
        onClick = viewContainer.makeFormatOperationEvent(SurroundedFormatOperation("*"))
    ) {
        Icon(Icons.Filled.FormatItalic, contentDescription = null)
    }
    ContentSpacer()
    EditorOperationButton(
        onClick = viewContainer.makeFormatOperationEvent(SurroundedFormatOperation("~~"))
    ) {
        Icon(Icons.Filled.FormatStrikethrough, contentDescription = null)
    }
    ContentSpacer()
    EditorOperationButton(
        onClick = viewContainer.makeFormatOperationEvent(SurroundedFormatOperation("[](", ")"))
    ) {
        Icon(Icons.Filled.Link, contentDescription = null)
    }
    ContentSpacer()
    EditorOperationButton(
        onClick = viewContainer.makeFormatOperationEvent(SurroundedFormatOperation("\n```\n", "```\n"))
    ) {
        Icon(Icons.Filled.Code, contentDescription = null)
    }
    ContentSpacer()
    EditorOperationButton(
        onClick = viewContainer.makeFormatOperationEvent(LineStartFormatOperation("# "))
    ) {
        Icon(Icons.Filled.Title, contentDescription = null)
    }
    ContentSpacer()
    EditorOperationButton(
        onClick = viewContainer.makeFormatOperationEvent(LineStartFormatOperation("> "))
    ) {
        Icon(Icons.Filled.FormatQuote, contentDescription = null)
    }
}

@Composable
fun EditorOperationButton(
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        content = content,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = Color.Unspecified
        ),
    )
}

fun AndroidViewContainer<EditText>.makeFormatOperationEvent(formatOperation: FormatOperation): () -> Unit {
    return {
        letView (formatOperation::operate)
    }
}

fun interface FormatOperation {
    fun operate(editText: EditText)
}

class SurroundedFormatOperation(
    private val before: String,
    private val after: String
): FormatOperation {
    constructor(surrounded: String): this(surrounded, surrounded)

    override fun operate(editText: EditText) {
        editText.text.insert(editText.selectionStart, before)
        editText.text.insert(editText.selectionEnd, after)
        editText.setSelection(editText.selectionStart - after.length)
    }
}

class LineStartFormatOperation(
    private val start: String
): FormatOperation {
    override fun operate(editText: EditText) {
        var lineStart = editText.selectionEnd - 1
        val text = editText.text
        while (lineStart > 0 && text[lineStart] != '\n' && text[lineStart] != '\r') lineStart--
        editText.text.insert(lineStart + 1, start)
    }
}