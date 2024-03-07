package com.example.madtwoassignmentone.views.role

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.madtwoassignmentone.AppViewModelProvider
import com.example.madtwoassignmentone.BottomBar
import com.example.madtwoassignmentone.R
import com.example.madtwoassignmentone.TopBar
import com.example.madtwoassignmentone.models.RoleModel
import com.example.madtwoassignmentone.navigation.NavigationDestination
import kotlinx.coroutines.launch


object RoleDetailsDestination : NavigationDestination {
    override val route = "role_details"
    override val titleRes = R.string.role_details
    const val roleIdArg = "roleId"
    val routeWithArgs = "$route/{$roleIdArg}"
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoleDetailsScreen(
    navigateBack: () -> Unit,
    navigateToEditRole: (Long) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RoleDetailsModel = viewModel(factory = AppViewModelProvider.Factory)
) {

    val uiState = viewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()


    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(RoleDetailsDestination.titleRes),
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack,
            )
        },
        bottomBar = { BottomBar() },
    )

    { innerPadding ->
        LazyColumn(
            modifier = modifier.padding(innerPadding)
        ) {
            item {
                RoleDetailsBody(
                    roleDetailsUiState = uiState.value,
                    onDelete = {
                        coroutineScope.launch {
                            viewModel.deleteRole()
                            navigateBack()
                        }
                    },
                    onEdit = {
                        navigateToEditRole(uiState.value.roleDetails.id)
                    },

                )

            }

        }
    }
}


@Composable
private fun RoleDetailsBody(
    roleDetailsUiState: RoleDetailsUiState,
    onDelete: () -> Unit,
    onEdit: () -> Unit,
    modifier: Modifier = Modifier

) {

    var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }


    Column(
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ) {

        RoleDetails(
            role = roleDetailsUiState.roleDetails.toRole(),
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = { deleteConfirmationRequired = true},
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.small,
        ) {
            Text(stringResource(R.string.delete))
        }
        Button(
            onClick = onEdit,
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.small,
        ){
            Text(stringResource(R.string.edit))
        }
        if (deleteConfirmationRequired) {
            DeleteConfirmationDialog(
                onDeleteConfirm = {
                    deleteConfirmationRequired = false
                    onDelete()
                },
                onDeleteCancel = { deleteConfirmationRequired = false },
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium))
            )
        }



    }
}
@Composable
fun RoleDetails(
    role: RoleModel, modifier: Modifier = Modifier
) {


    Card(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_medium)),
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(id = R.dimen.padding_medium)
            )
        )

        {

            RoleDetailsRow(
                labelResID = R.string.role,
                roleDetail = role.name,
                modifier = Modifier
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.padding_medium)
                    )

            )
            RoleDetailsRow(
                labelResID = R.string.role_desc,
                roleDetail = role.description,
                modifier = Modifier
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.padding_medium)
                    )

            )
            RoleDetailsRow(
                labelResID = R.string.champion_winRate,
                roleDetail = role.winRate.toString(),
                modifier = Modifier
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.padding_medium)
                    )
            )
        }

    }
}
@Composable
private fun RoleDetailsRow(
    @StringRes labelResID: Int, roleDetail: String, modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_medium))) {
        Text(stringResource(labelResID), fontWeight = FontWeight.Bold)
        Text(text = roleDetail)
    }
}

@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(onDismissRequest = { },
        title = { Text(stringResource(R.string.attention)) },
        text = { Text(stringResource(R.string.delete_question)) },
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text(stringResource(R.string.no))
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(stringResource(R.string.yes))
            }
        })
}