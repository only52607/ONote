package com.ooooonly.onote.ui.components

import android.content.Context
import android.view.Gravity
import android.widget.EditText
import android.widget.TextView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.widget.addTextChangedListener
import io.noties.markwon.Markwon
import io.noties.markwon.editor.MarkwonEditor
import io.noties.markwon.editor.MarkwonEditorTextWatcher
import io.noties.markwon.ext.latex.JLatexMathPlugin
import io.noties.markwon.ext.strikethrough.StrikethroughPlugin
import io.noties.markwon.ext.tables.TablePlugin
import io.noties.markwon.ext.tasklist.TaskListPlugin
import io.noties.markwon.html.HtmlPlugin
import io.noties.markwon.image.coil.CoilImagesPlugin
import io.noties.markwon.inlineparser.MarkwonInlineParserPlugin
import java.util.concurrent.Executors

fun Context.buildMarkwon(textSize: Float = 10f): Markwon =
    Markwon.builder(this)
        .usePlugin(TaskListPlugin.create(this))
        .usePlugin(StrikethroughPlugin.create())
        .usePlugin(TablePlugin.create(this))
        .usePlugin(CoilImagesPlugin.create(this))
        .usePlugin(HtmlPlugin.create())
//        .usePlugin(MarkwonInlineParserPlugin.create())
//        .usePlugin(JLatexMathPlugin.create(textSize) {
//            it.inlinesEnabled(true)
//        })
        .build()

@Composable
fun MarkwonComposable(
    modifier: Modifier,
    content: String,
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            TextView(context).also { textView ->
                textView.tag = context.buildMarkwon(textView.textSize).also {
                    it.setMarkdown(textView, content)
                }
            }
        },
        update = { textView ->
            (textView.tag as Markwon).setMarkdown(textView, content)
        }
    )
}

@Composable
fun MarkwonEditorComposable(
    modifier: Modifier,
    content: String,
    onContentChanged: (String) -> Unit
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            EditText(context).apply {
                val markwon = context.buildMarkwon(textSize)
                val editor = MarkwonEditor.create(markwon)
                gravity = Gravity.TOP
                tag = markwon
                addTextChangedListener(MarkwonEditorTextWatcher.withPreRender(
                    editor,
                    Executors.newCachedThreadPool(),
                    this
                ))
                addTextChangedListener {
                    onContentChanged(it.toString())
                }
                setText(content)
            }
        },
        update = { textView ->
//            if (textView.text.toString() != content) {
//                textView.setText(content)
//            }
        }
    )
}