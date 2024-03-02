package com.example.madtwoassignmentone.views.home

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.madtwoassignmentone.HomeAddViewModelFactory
import com.example.madtwoassignmentone.HomeViewModelFactory
import com.example.madtwoassignmentone.navigation.NavigationDestination
import com.example.madtwoassignmentone.R
import com.example.madtwoassignmentone.ViewModelProvider
import com.example.madtwoassignmentone.models.ChampionRepository

object HomeAddDestination : NavigationDestination {
    override val route = "home_add"
    override val titleRes = R.string.add_champion
    val routeWithArgs = route
}

@Composable
fun HomeAddScreen(navigateBack: () -> Unit, viewModel: HomeAddModel = viewModel(factory = ViewModelProvider().homeAddViewModelFactory)) {

}
