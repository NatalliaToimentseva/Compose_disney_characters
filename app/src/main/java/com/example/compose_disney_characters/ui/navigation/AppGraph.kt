package com.example.compose_disney_characters.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.compose_disney_characters.ui.screens.details.DetailsDestination
import com.example.compose_disney_characters.ui.screens.homeScreen.HomeDestination

private const val ID = "id"

@Composable
fun AppGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = ScreenRoute.Home.route) {
        composable(ScreenRoute.Home.route) {
            HomeDestination(navController)
        }
        composable(
            ScreenRoute.Details.route,
            arguments = listOf(navArgument(ID) { type = NavType.IntType })
        ) { backStack ->
            backStack.arguments?.getInt(ID)?.let { id ->
                DetailsDestination(id, navController)
            }
        }
    }
}