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
    import com.example.madtwoassignmentone.views.home.HomeDestination
    import com.example.madtwoassignmentone.views.home.HomeScreen


    /**
     * Provides Navigation graph for the application.
     */
    @Composable
    fun ToDoNavHost(
        navController: NavHostController,
        modifier: Modifier = Modifier,
        championRepository: ChampionRepository,


    ) {
        NavHost(
            navController = navController,
            startDestination = StartDestination.route,
            modifier = modifier
        ) {
            composable(route = StartDestination.route) {
                StartScreen(
                    navigateToHome = { navController.navigate(HomeDestination.route) }
                )
            }
            composable(route = HomeDestination.route) {
                HomeScreen(championRepository)
            }
            }
        }


