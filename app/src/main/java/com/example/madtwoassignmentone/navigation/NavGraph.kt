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
import com.example.madtwoassignmentone.views.role.RoleAddDestination
import com.example.madtwoassignmentone.views.role.RoleAddScreen
import com.example.madtwoassignmentone.views.role.RoleDestination
import com.example.madtwoassignmentone.views.role.RoleDetails
import com.example.madtwoassignmentone.views.role.RoleDetailsDestination
import com.example.madtwoassignmentone.views.role.RoleDetailsScreen
import com.example.madtwoassignmentone.views.role.RoleEditDestination
import com.example.madtwoassignmentone.views.role.RoleEditScreen
import com.example.madtwoassignmentone.views.role.RoleEntryBody
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
navController.navigate("${RoleDetailsDestination.route}/${it}")

                })
        }
        composable(route=RoleAddDestination.routeWithArgs){
            RoleAddScreen(navigateBack = { navController.popBackStack() },
            )
        }

        composable(
            route = RoleDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(RoleDetailsDestination.roleIdArg) {
                type = NavType.LongType
            })
        ) {
            RoleDetailsScreen(
                navigateToEditRole = { navController.navigate("${RoleEditDestination.route}/$it") },
                navigateBack = { navController.navigateUp() },

                )
        }

        composable(
            route = RoleEditDestination.routeWithArgs,
            arguments = listOf(navArgument(RoleEditDestination.roleIdArg) {
                type = NavType.LongType
            })
        ) {

            RoleEditScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }

    }
}