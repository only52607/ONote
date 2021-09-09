package com.ooooonly.onote.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ooooonly.onote.di.AppContainer
import com.ooooonly.onote.model.NoteViewModel
import com.ooooonly.onote.model.TodoViewModel
import com.ooooonly.onote.ui.navigation.ONoteBottomNavigationBar
import com.ooooonly.onote.ui.navigation.ONoteNavGraph
import com.ooooonly.onote.ui.navigation.Screen
import com.ooooonly.onote.ui.theme.ONoteTheme

@Composable
fun ONoteApp(
    noteViewModel: NoteViewModel,
    todoViewModel: TodoViewModel,
    appContainer: AppContainer
){
    ONoteTheme {
        ProvideWindowInsets {
            val systemUiController = rememberSystemUiController()
            SideEffect {
                systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = true)
            }
            val navController = rememberNavController()
            val scaffoldState = rememberScaffoldState()
            val currentSelectedItem by navController.currentScreenAsState()
            val coroutineScope = rememberCoroutineScope()
            Scaffold(
                scaffoldState = scaffoldState,
                bottomBar = {
                    ONoteBottomNavigationBar(
                        navController = navController,
                        modifier = Modifier.fillMaxWidth(),
                        selectedNavigation = currentSelectedItem
                    )
                },
                snackbarHost = {
                    SnackbarHost(it) { data -> Snackbar(snackbarData = data) }
                },
                drawerContent = {

                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {},
                        backgroundColor = MaterialTheme.colors.surface
                    ) {
                        Icon(Icons.Filled.Add, contentDescription = null)
                    }
                },
                floatingActionButtonPosition = FabPosition.Center,
                isFloatingActionButtonDocked = true
            ) { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)) {
                    ONoteNavGraph(
                        navController = navController,
                        scaffoldState = scaffoldState,
                        appContainer = appContainer,
                        noteViewModel = noteViewModel,
                        todoViewModel = todoViewModel
                    )
                }
            }
        }
    }
}


@Composable
private fun NavController.currentScreenAsState(): State<Screen> {
    val selectedItem = remember { mutableStateOf<Screen>(Screen.Note) }

    DisposableEffect(this) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            when {
                destination.hierarchy.any { it.route == Screen.Note.route } -> {
                    selectedItem.value = Screen.Note
                }
                destination.hierarchy.any { it.route == Screen.Todo.route } -> {
                    selectedItem.value = Screen.Todo
                }
            }
        }
        addOnDestinationChangedListener(listener)

        onDispose {
            removeOnDestinationChangedListener(listener)
        }
    }

    return selectedItem
}
