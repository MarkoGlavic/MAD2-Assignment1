
package com.example.madtwoassignmentone.views.home


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.madtwoassignmentone.BottomBar
import com.example.madtwoassignmentone.ViewModelProvider
import com.example.madtwoassignmentone.views.home.HomeViewModel
import com.example.madtwoassignmentone.R
import com.example.madtwoassignmentone.TopBar
import com.example.madtwoassignmentone.models.ChampionModel
import com.example.madtwoassignmentone.navigation.NavigationDestination
import com.example.madtwoassignmentone.views.home.HomeAddModel


object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name
    const val championId = "championId"
    val routeWithArgs = "$route/{$championId}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToAdd: (Long) -> Unit,
    navigateBack: () -> Unit,
    viewModel: HomeViewModel = viewModel(factory = ViewModelProvider().homeViewModelFactory))
{
    val homeUiState by viewModel.homeUiState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()


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

        floatingActionButton = {
            FloatingActionButton(
                onClick = {navigateToAdd(0) } ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) {
            innerPadding ->
        Column(
            modifier = modifier.padding(innerPadding)
        )
        {
            HomeBody(championList = homeUiState.championList, onItemClick = {}, modifier = modifier.padding(innerPadding))
        }}


}



@Composable
private fun HomeBody(
    championList: List<ChampionModel>, onItemClick: (Int) -> Unit, modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        if (championList.isEmpty()) {
            Text(
                text = stringResource(R.string.no_champions),
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        } else {
            HomeList(
                championList = championList,
                onItemClick = { },
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small))
            )
        }
    }
}

@Composable
private fun HomeList(
    championList: List<ChampionModel>, onItemClick: (ChampionModel) -> Unit, modifier: Modifier = Modifier
) {
    LazyVerticalGrid(   columns = GridCells.Adaptive(150.dp),
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(items = championList, key = { it.id }) { champion ->
            HomeChampion(champion = champion,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .clickable { onItemClick(champion) })
        }
    }
}


@Composable
private fun HomeChampion(
    champion: ChampionModel, modifier: Modifier = Modifier
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
                text = champion.name,
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                text = champion.winRate.toString(),
                style = MaterialTheme.typography.titleLarge,
            )

        }
    }
}