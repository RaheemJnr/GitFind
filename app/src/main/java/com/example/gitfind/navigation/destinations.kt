package com.example.gitfind.navigation

/** sealed class to hold possible screen destination and screen title */

sealed class MainScreen(val route: String) {
    object GitListScreen : MainScreen("gitListScreen")
    //object DetailsList : MainScreen("detail")

}