package com.example.madtwoassignmentone.views.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.madtwoassignmentone.HomeViewModelFactory
import com.example.madtwoassignmentone.models.ChampionModel
import com.example.madtwoassignmentone.views.home.HomeViewModel
import com.example.madtwoassignmentone.R
import com.example.madtwoassignmentone.models.ChampionRepository
import com.example.madtwoassignmentone.models.ChampionStore
import com.example.madtwoassignmentone.navigation.NavigationDestination

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun HomeScreen( navigateToAdd: ()->Unit, viewModel: HomeViewModel = viewModel(factory = com.example.madtwoassignmentone.ViewModelProvider().homeViewModelFactory)) {


    val homeUiState by viewModel.homeUiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
            )
        },

        floatingActionButton = {
            FloatingActionButton(onClick = {navigateToAdd() }) {

            }
        },
        content = {
            HomeContent(championList = homeUiState.championList)
        }
    )
}

@Composable
fun HomeContent(championList: List<ChampionModel>) {
    Column(
        modifier = Modifier

            .fillMaxSize(),
    ) {
        Text(text = "Champions:",)

        if (championList.isEmpty()) {
            Text(text = "No champions found")
        } else {
            ChampionList(championList = championList)
        }
    }
}

@Composable
fun ChampionList(championList: List<ChampionModel>) {
    Column {
        championList.forEach { champion ->
            Text(text = champion.name)

        }
    }
}
