package com.ooooonly.onote.ui.navigation

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ooooonly.onote.R
import com.ooooonly.onote.di.AppContainer
import com.ooooonly.onote.model.NoteViewModel
import com.ooooonly.onote.model.TodoViewModel
import com.ooooonly.onote.ui.note.NoteScreen
import com.ooooonly.onote.ui.note.edit.NoteEditorScreen
import com.ooooonly.onote.ui.todo.TodoScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ONoteNavGraph(
    navController: NavHostController,
    scaffoldState: ScaffoldState,
    startDestination: String = Screen.Note.route,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    appContainer: AppContainer,
    noteViewModel: NoteViewModel,
    todoViewModel: TodoViewModel
) {
    val navigationIcon: @Composable () -> Unit = {
        IconButton(onClick = {
            if (scaffoldState.drawerState.isClosed) {
                coroutineScope.launch { scaffoldState.drawerState.open() }
            } else {
                coroutineScope.launch { scaffoldState.drawerState.close() }
            }
        }) {
            Icon(Icons.Filled.Menu, contentDescription = stringResource(R.string.drawer_title))
        }
    }
    NavHost(navController = navController, startDestination = startDestination) {
        addNoteTopLevel(
            navController = navController,
            noteViewModel = noteViewModel,
            scaffoldState = scaffoldState,
            navigationIcon = navigationIcon
        )
        addTodoTopLevel(
            navController = navController,
            todoViewModel = todoViewModel,
            navigationIcon = navigationIcon,
            scaffoldState = scaffoldState,
        )
    }
}

private fun NavGraphBuilder.addNoteTopLevel(
    navController: NavController,
    noteViewModel: NoteViewModel,
    scaffoldState: ScaffoldState,
    navigationIcon: @Composable () -> Unit
) {
    navigation(
        route = Screen.Note.route,
        startDestination = LeafScreen.Note.createRoute(Screen.Note)
    ) {
        composable(LeafScreen.Note.createRoute(Screen.Note)) {
            NoteScreen(
                noteViewModel = noteViewModel,
                navigationIcon = navigationIcon,
                toEditor = {
                    navController.navigate(LeafScreen.NoteEditor.createRoute(Screen.Note))
                }
            )
        }
        composable(LeafScreen.NoteEditor.createRoute(Screen.Note)) {
            NoteEditorScreen(
                noteViewModel = noteViewModel,
                onFinished = {
                    navController.popBackStack()
                },
                onBackPressed = {
                    navController.popBackStack()
                }
            )
        }
    }
}

private fun NavGraphBuilder.addTodoTopLevel(
    navController: NavController,
    todoViewModel: TodoViewModel,
    scaffoldState: ScaffoldState,
    navigationIcon: @Composable () -> Unit
) {
    navigation(
        route = Screen.Todo.route,
        startDestination = LeafScreen.Todo.createRoute(Screen.Todo)
    ) {
        composable(LeafScreen.Todo.createRoute(Screen.Todo)) {
            TodoScreen(
                todoViewModel = todoViewModel,
                navigationIcon = navigationIcon
            )
        }
    }
}