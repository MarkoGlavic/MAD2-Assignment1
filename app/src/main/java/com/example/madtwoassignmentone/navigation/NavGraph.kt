package com.example.madtwoassignmentone.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.madtwoassignmentone.models.ChampionRepository
import com.example.madtwoassignmentone.views.StartDestination
import com.example.madtwoassignmentone.views.StartScreen
import com.example.madtwoassignmentone.views.home.HomeAddDestination
import com.example.madtwoassignmentone.views.home.HomeAddScreen
import com.example.madtwoassignmentone.views.home.HomeDestination
import com.example.madtwoassignmentone.views.home.HomeScreen
import com.example.madtwoassignmentone.views.role.RoleAddDestination
import com.example.madtwoassignmentone.views.role.RoleAddScreen
import com.example.madtwoassignmentone.views.role.RoleDestination
import com.example.madtwoassignmentone.views.role.RoleScreen


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
                navigateToHome = { navController.navigate(RoleDestination.route) }
            )
        }
        composable(route = RoleDestination.route){
            RoleScreen(navigateToAdd = { navController.navigate(RoleAddDestination.route) },
                navigateToRole ={
                    navController.navigate("${HomeDestination.route}/${it}")
                })
        }
        composable(route=RoleAddDestination.routeWithArgs){
            RoleAddScreen(navigateBack = { navController.popBackStack() },
       )
        }

        composable(
            route = HomeDestination.routeWithArgs,
            arguments = listOf(navArgument(HomeDestination.championId) {
                type = NavType.LongType
            })
        ) {
            HomeScreen(navigateToAdd = {
                navController.navigate("${HomeAddDestination.route}/$it")
            },
                navigateBack = { navController.popBackStack() }

            )
        }
        composable(route = HomeAddDestination.routeWithArgs,
            arguments = listOf(navArgument(HomeAddDestination.championIdArg) {
                type = NavType.LongType
            })
        )
        {
            HomeAddScreen(navigateBack = {navController.popBackStack()}
            )
        }
    }
}