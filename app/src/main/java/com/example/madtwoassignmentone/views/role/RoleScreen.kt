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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.madtwoassignmentone.BottomBar
import com.example.madtwoassignmentone.R
import com.example.madtwoassignmentone.TopBar
import com.example.madtwoassignmentone.ViewModelProvider
import com.example.madtwoassignmentone.models.ChampionModel
import com.example.madtwoassignmentone.models.RoleModel
import com.example.madtwoassignmentone.navigation.NavigationDestination
import com.example.madtwoassignmentone.views.home.HomeDestination
import com.example.madtwoassignmentone.views.home.HomeViewModel

object RoleDestination : NavigationDestination {
    override val route = "role"
    override val titleRes = R.string.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoleScreen(
    modifier: Modifier = Modifier,
    navigateToAdd: () -> Unit,
    viewModel: RoleViewModel = viewModel(factory = ViewModelProvider().roleViewModelFactory)
)
{
    val roleUiState by viewModel.roleUiState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()


    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(HomeDestination.titleRes),
                canNavigateBack = true,

            )
        },

        bottomBar = {
            BottomBar()
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToAdd  ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) {
            innerPadding ->
        Column(
            modifier = modifier.padding(innerPadding)
        )
        {
            HomeBody(roleList = roleUiState.roleList, onItemClick = {}, modifier = modifier.padding(innerPadding))
        }
    }


}



@Composable
private fun HomeBody(
    roleList: List<RoleModel>, onItemClick: (Int) -> Unit, modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        if (roleList.isEmpty()) {
            Text(
                text = stringResource(R.string.no_champions),
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        } else {
            RoleList(
                roleList = roleList,
                onItemClick = { },
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small))
            )
        }
    }
}

@Composable
private fun RoleList(
    roleList: List<RoleModel>, onItemClick: (RoleModel) -> Unit, modifier: Modifier = Modifier
) {
    LazyVerticalGrid(   columns = GridCells.Adaptive(150.dp),
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(items = roleList, key = { it.id }) { role ->
            Role(role = role,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
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
                text = role.name,
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                text = role.description,
                style = MaterialTheme.typography.titleLarge,
            )

        }
    }
}
