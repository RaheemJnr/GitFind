package com.example.gitfind.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gitfind.ui.screens.GitHubListScreen


/** nav graph to navigate to respective screens */
@ExperimentalMaterialApi
@Composable
fun MainScreenNavigation() {

    val navController = rememberNavController()

    NavHost(navController, startDestination = MainScreen.GitListScreen.route) {
        //List
        composable(MainScreen.GitListScreen.route) {
            GitHubListScreen()
        }
        //
//        composable(MainScreen.DetailsList.route) {
//            // WeatherDetailScreen()
//        }

    }


}
