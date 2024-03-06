package com.example.madtwoassignmentone.views.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.madtwoassignmentone.BottomBar
import com.example.madtwoassignmentone.models.ChampionModel
import com.example.madtwoassignmentone.models.IdGenerator
import com.example.madtwoassignmentone.navigation.NavigationDestination
import com.example.madtwoassignmentone.R
import com.example.madtwoassignmentone.TopBar
import com.example.madtwoassignmentone.ViewModelProvider


object HomeAddDestination : NavigationDestination {
    override val route = "home_add"
    override val titleRes = R.string.add_champion
    const val championIdArg = "championId"
    val routeWithArgs = "$route/{$championIdArg}"
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAddScreen(navigateBack: () -> Unit, viewModel: HomeAddModel = viewModel(factory = ViewModelProvider().homeAddViewModelFactory)) {

    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(HomeDestination.titleRes),
                canNavigateBack = true,
                navigateUp = navigateBack
                )
        },

        bottomBar = {
            BottomBar()
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var championName by remember { mutableStateOf("") }

                OutlinedTextField(
                    value = championName,
                    onValueChange = { championName = it },
                    label = { Text("Champion Name") }
                )

                var winRate by remember { mutableStateOf("") }

                OutlinedTextField(
                    value = winRate,
                    onValueChange = {winRate = it} )


                Button(
                    onClick = {
                        val champion = ChampionModel(IdGenerator.generateId(), championName, winRate.toInt())
                        viewModel.addChampion(champion)
                        navigateBack()
                    }
                ) {
                    Text(text = "Add Champion")
                }
            }
        }
    )
}