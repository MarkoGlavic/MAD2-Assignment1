package com.example.madtwoassignmentone.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.madtwoassignmentone.views.StartDestination
import com.example.madtwoassignmentone.views.StartScreen


/**
 * Provides Navigation graph for the application.
 */
@Composable
fun ToDoNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = StartDestination.route,
        modifier = modifier
    ) {
        composable(route = StartDestination.route) {
            StartScreen(
                navigateToHome = { navController.navigate(StartDestination.route) },

                )
        }
    }
}
