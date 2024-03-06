package com.example.madtwoassignmentone.views.role

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.madtwoassignmentone.AppViewModelProvider
import com.example.madtwoassignmentone.BottomBar
import com.example.madtwoassignmentone.models.IdGenerator
import com.example.madtwoassignmentone.navigation.NavigationDestination
import com.example.madtwoassignmentone.R
import com.example.madtwoassignmentone.TopBar
import com.example.madtwoassignmentone.models.RoleModel
import com.example.madtwoassignmentone.views.home.HomeDestination
import kotlinx.coroutines.launch


object RoleAddDestination : NavigationDestination {
    override val route = "role_add"
    override val titleRes = R.string.add_role
    val routeWithArgs = route
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoleAddScreen(navigateBack: () -> Unit,
                  viewModel: RoleAddModel = viewModel(factory = AppViewModelProvider.Factory)

){


    var showDialog by remember { mutableStateOf(false) }
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
        }
    )
    { innerPadding ->
        RoleEntryBody(
            roleUiState = viewModel.roleUiState,
            onRoleValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveItem()
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
fun RoleEntryBody(
    roleUiState: RoleUiState,
    onRoleValueChange: (RoleDetails) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_large)),
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium))
    ) {
        RoleInputForm(
            roleDetails = roleUiState.roleDetails,
            onValueChange = onRoleValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            enabled = roleUiState.isEntryValid,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.save))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoleInputForm(
    roleDetails: RoleDetails,
    modifier: Modifier = Modifier,
    onValueChange: (RoleDetails) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ) {
        OutlinedTextField(
            value = roleDetails.name,
            onValueChange = { onValueChange(roleDetails.copy(name = it)) },
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
            value = roleDetails.description,
            onValueChange = { onValueChange(roleDetails.copy(description = it)) },
            label = { Text(stringResource(R.string.role_desc)) },
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

