package com.example.madtwoassignmentone.views.role

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.madtwoassignmentone.AppViewModelProvider
import com.example.madtwoassignmentone.BottomBar
import com.example.madtwoassignmentone.R
import com.example.madtwoassignmentone.TopBar
import com.example.madtwoassignmentone.models.ChampionModel
import com.example.madtwoassignmentone.models.RoleModel
import com.example.madtwoassignmentone.navigation.NavigationDestination
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

object RoleDestination : NavigationDestination {
    override val route = "role"
    override val titleRes = R.string.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoleScreen(
    modifier: Modifier = Modifier,
    navigateToAdd: () -> Unit,
    navigateToRole:(Long) -> Unit,
    viewModel: RoleViewModel = viewModel(factory = AppViewModelProvider.Factory)


) {
    val uiState by viewModel.homeUiState.collectAsState()
    val searchText = remember { mutableStateOf("") }
    var selectedWinRate = remember { mutableStateOf(0) }


    val filteredItems = if (searchText.value != "") {
        viewModel.getName(searchText.value).collectAsState(initial = listOf()).value
    } else {
        uiState.roleList
    }

    val itemList = if (selectedWinRate.value > 0) {
        viewModel.getWinRate(selectedWinRate.value).collectAsState(initial = listOf()).value
    } else {
        filteredItems
    }

    val homeUiState by viewModel.homeUiState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()


    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(RoleDestination.titleRes),
                canNavigateBack = false,
                scrollBehavior = scrollBehavior


            )
        },

        bottomBar = {
            BottomBar()
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToAdd
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
                println(viewModel.homeUiState.value.roleList)
            }
        }
    ) { innerPadding ->
        Column(
            modifier = modifier.padding(innerPadding)
        ) {
            OutlinedTextField(
                value = searchText.value,
                onValueChange = { newText ->
                    searchText.value = newText
                },
                label = { Text(text = stringResource(R.string.search)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )

            OutlinedTextField(
                value = selectedWinRate.value.toString(),
                onValueChange = { newValue ->
                    var score = newValue.toIntOrNull() ?: 0
                    selectedWinRate.value = score
                },
                label = { Text(text = stringResource(id = R.string.champion_winRate)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )

            HomeBody(
                roleList = itemList,
                onItemClick = navigateToRole,
                modifier = modifier.padding(innerPadding)
            )
        }

    }
}












@Composable
private fun HomeBody(
    roleList: List<RoleModel>, onItemClick: (Long) -> Unit, modifier: Modifier = Modifier


)

{
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        if (roleList.isEmpty()) {
            Text(
                text = stringResource(R.string.no_roles),
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        } else {
            RoleList(
                roleList = roleList,
                onItemClick = { onItemClick(it.id)},
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small))
            )
        }
    }
}

@Composable
private fun RoleList(
    roleList: List<RoleModel>, onItemClick: (RoleModel) -> Unit, modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(1), // Set only one column to have one entry per row
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(items = roleList, key = { it.id }) { role ->
            Role(
                role = role,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
                    .clickable { onItemClick(role) })


        }
    }
}


@Composable
private fun Role(
    role: RoleModel, modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
        ) {
            Text(
                text = "${stringResource(R.string.role_name_label)} ${role.name}",
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                text = "${stringResource(R.string.role_description_label)} ${role.description}",
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                text = "${stringResource(R.string.role_winrate_label)} ${role.winRate}",
                style = MaterialTheme.typography.titleLarge,
            )
        }
    }
}
