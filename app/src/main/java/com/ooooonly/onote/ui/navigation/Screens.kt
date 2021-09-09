package com.ooooonly.onote.ui.navigation

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