package com.example.madtwoassignmentone.views.role

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.madtwoassignmentone.AppViewModelProvider
import com.example.madtwoassignmentone.BottomBar
import com.example.madtwoassignmentone.navigation.NavigationDestination
import com.example.madtwoassignmentone.R
import com.example.madtwoassignmentone.TopBar
import kotlinx.coroutines.launch

object RoleEditDestination : NavigationDestination {
    override val route = "role_edit"
    override val titleRes = R.string.role_edit
    const val roleIdArg = "roleId"
    val routeWithArgs = "$route/{$roleIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoleEditScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RoleEditModel = viewModel(factory = AppViewModelProvider.Factory)
)
{

    val coroutineScope = rememberCoroutineScope()


    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(RoleDetailsDestination.titleRes),
                canNavigateBack = true,
                navigateUp = onNavigateUp,
            )
        },
        bottomBar = { BottomBar() },
    )
    { innerPadding ->
        RoleEntryBody(
            roleUiState = viewModel.roleUiState,
            onRoleValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateRole()
                    navigateBack()
                }
            },
            modifier = modifier.padding(innerPadding)
        )
    }
}