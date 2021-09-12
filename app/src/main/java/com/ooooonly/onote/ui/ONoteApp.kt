package com.ooooonly.onote.ui

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ooooonly.onote.di.AppContainer
import com.ooooonly.onote.model.NoteViewModel
import com.ooooonly.onote.model.TodoViewModel
import com.ooooonly.onote.ui.navigation.*
import com.ooooonly.onote.ui.theme.ONoteTheme
import com.ooooonly.onote.ui.todo.edit.TodoEditorView
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterialApi::class)
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
            val bottomDrawerState = rememberBottomDrawerState(BottomDrawerValue.Closed)
            val currentSelectedItem by navController.currentScreenAsState()
            val coroutineScope = rememberCoroutineScope()
            BottomDrawer(
                drawerState = bottomDrawerState,
                gesturesEnabled = bottomDrawerState.isExpanded,
                drawerContent = {
                    TodoEditorView(
                        todoViewModel = todoViewModel,
                        onDone = {
                            coroutineScope.launch {
                                bottomDrawerState.close()
                            }
                        }
                    )
                },
                content = {
                    Home(
                        navController = navController,
                        noteViewModel = noteViewModel,
                        todoViewModel = todoViewModel,
                        appContainer = appContainer,
                        onFloatingButtonClick = {
                            if (currentSelectedItem.route == Screen.Note.route) {
                                noteViewModel.createNewEditingState()
                                navController.navigate(LeafScreen.NoteEditor.createRoute(Screen.Note))
                            } else {
                                coroutineScope.launch {
                                    todoViewModel.createEmptyEditingTodoState()
                                    bottomDrawerState.open()
                                }
                            }
                        }
                    )
                }
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Home(
    navController: NavHostController,
    noteViewModel: NoteViewModel,
    todoViewModel: TodoViewModel,
    appContainer: AppContainer,
    onFloatingButtonClick: () -> Unit,
) {
    val scaffoldState = rememberScaffoldState()
    val currentSelectedItem by navController.currentScreenAsState()
    val bottomNavigationVisible by navController.bottomNavigationVisibleState()
    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            if (bottomNavigationVisible) {
                ONoteBottomNavigationBar(
                    navController = navController,
                    modifier = Modifier.fillMaxWidth(),
                    selectedNavigation = currentSelectedItem
                )
            }
        },
        snackbarHost = {
            SnackbarHost(it) { data -> Snackbar(snackbarData = data) }
        },
        drawerContent = {

        },
        floatingActionButton = {
            if (bottomNavigationVisible) {
                FloatingActionButton(
                    onClick = onFloatingButtonClick,
                    backgroundColor = MaterialTheme.colors.surface
                ) {
                    Icon(Icons.Filled.Add, contentDescription = null)
                }
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