package com.ooooonly.onote.ui.navigation

import androidx.compose.runtime.*
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy

sealed class Screen(val route: String) {
    object Note : Screen("note")
    object Todo : Screen("todo")
}

sealed class LeafScreen(
    private val route: String
) {
    fun createRoute(root: Screen) = "${root.route}/$route"

    object Note : LeafScreen("note")

    object Todo : LeafScreen("todo")
}

@Composable
fun NavController.currentScreenAsState(): State<Screen> {
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
