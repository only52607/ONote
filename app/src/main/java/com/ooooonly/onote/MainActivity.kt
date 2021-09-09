package com.ooooonly.onote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ooooonly.onote.di.AppContainer
import com.ooooonly.onote.model.NoteViewModel
import com.ooooonly.onote.model.TodoViewModel
import com.ooooonly.onote.ui.ONoteApp
import com.ooooonly.onote.ui.theme.ONoteTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val noteViewModel: NoteViewModel by viewModels()
    private val todoViewModel: TodoViewModel by viewModels()
    @Inject lateinit var appContainer: AppContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_ONote)
        super.onCreate(savedInstanceState)
        setContent {
            ONoteApp(
                noteViewModel = noteViewModel,
                todoViewModel = todoViewModel,
                appContainer = appContainer
            )
        }
    }
}