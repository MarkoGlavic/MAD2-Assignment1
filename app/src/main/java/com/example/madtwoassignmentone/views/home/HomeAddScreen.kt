package com.example.madtwoassignmentone.views.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.madtwoassignmentone.AppViewModelProvider
import com.example.madtwoassignmentone.BottomBar
import com.example.madtwoassignmentone.navigation.NavigationDestination
import com.example.madtwoassignmentone.R
import com.example.madtwoassignmentone.TopBar
import kotlinx.coroutines.launch


object HomeAddDestination : NavigationDestination {
    override val route = "home_add"
    override val titleRes = R.string.add_champion
    const val championIdArg = "championId"
    val routeWithArgs = "$route/{$championIdArg}"
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAddScreen(navigateBack: () -> Unit, viewModel: HomeAddModel = viewModel(factory = AppViewModelProvider.Factory)) {

    val uiState = viewModel.uiState.collectAsState()
    val champions = viewModel.champions.collectAsState()
    val coroutineScope = rememberCoroutineScope()



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
        })
        { innerPadding ->
            ChampionEntryBody(
                championUiState = viewModel.championUiState,
                onChampionValueChange = viewModel::updateChampionState,
                onSaveClick = {
                    coroutineScope.launch {
                        viewModel.saveChampion()
                        navigateBack()

                    }
                },
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                    .fillMaxWidth()
            )
        }



}
    @Composable
    fun ChampionEntryBody(
        championUiState: ChampionUiState,
        onChampionValueChange: (ChampionDetails) -> Unit,
        onSaveClick: () -> Unit,
        modifier: Modifier = Modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_large)),
            modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium))
        ) {
            ChampionInputForm(
                championDetails = championUiState.championDetails,
                onValueChange = onChampionValueChange,
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = onSaveClick,
                enabled = championUiState.isEntryValid,
                shape = MaterialTheme.shapes.small,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(R.string.save))
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ChampionInputForm(
        championDetails: ChampionDetails,
        modifier: Modifier = Modifier,
        onValueChange: (ChampionDetails) -> Unit = {},
        enabled: Boolean = true
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
        ) {
            OutlinedTextField(
                value = championDetails.name,
                onValueChange = { onValueChange(championDetails.copy(name = it)) },
                label = { Text(stringResource(R.string.role_name)) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Green,
                    unfocusedBorderColor = Color.Yellow
                ),
                modifier = Modifier.fillMaxWidth(),
                enabled = enabled,
                singleLine = true
            )
            OutlinedTextField(
                value = championDetails.winRate.toString(),
                onValueChange = { onValueChange(championDetails.copy(winRate = it.toInt())) },
                label = { Text(stringResource(R.string.champion_winRate)) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Green,
                    unfocusedBorderColor = Color.Yellow
                ),
                modifier = Modifier.fillMaxWidth(),
                enabled = enabled,
                singleLine = true
            )


            if (enabled) {
                Text(
                    text = stringResource(R.string.required_fields),
                    modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_medium))
                )
            }

        }
    }



