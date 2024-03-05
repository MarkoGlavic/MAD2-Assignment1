package com.example.madtwoassignmentone.views.role

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.madtwoassignmentone.BottomBar
import com.example.madtwoassignmentone.models.IdGenerator
import com.example.madtwoassignmentone.navigation.NavigationDestination
import com.example.madtwoassignmentone.R
import com.example.madtwoassignmentone.TopBar
import com.example.madtwoassignmentone.ViewModelProvider
import com.example.madtwoassignmentone.models.RoleModel
import com.example.madtwoassignmentone.views.home.HomeDestination


object RoleAddDestination : NavigationDestination {
    override val route = "role_add"
    override val titleRes = R.string.add_role
    val routeWithArgs = route
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoleAddScreen(navigateBack: () -> Unit,
                  viewModel: RoleAddModel = viewModel(factory = ViewModelProvider().roleAddFactory)){

    var showDialog by remember { mutableStateOf(false) }

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
                        .padding(64.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    var roleName by remember { mutableStateOf("") }

                    OutlinedTextField(
                        value = roleName,
                        label = { Text(stringResource(R.string.role_name)) },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.Green,
                            unfocusedBorderColor = Color.Yellow
                        ),
                        onValueChange = { roleName = it }

                    )

                    var description by remember { mutableStateOf("") }

                    OutlinedTextField(
                        value = description,
                        onValueChange = { description = it },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.Green,
                            unfocusedBorderColor = Color.Yellow
                        ),
                        label = { Text(stringResource(R.string.role_desc)) }
                    )



                    Button(
                        onClick = {
                            if (roleName.isNotBlank() && description.isNotBlank()) {
                                val role =
                                    RoleModel(IdGenerator.generateId(), roleName, description)
                                viewModel.addRole(role)
                                navigateBack()
                            } else {
                                showDialog = true

                            }
                        }
                    ) {
                        Text(stringResource(R.string.add_role))
                    }
                    if (showDialog) {
                        AlertDialog(
                            onDismissRequest = { showDialog = false },
                            title = { Text(stringResource(R.string.alert)) },
                            text = { Text(stringResource(R.string.role_req)) },
                            confirmButton = {
                                TextButton(
                                    onClick = { showDialog = false },
                                ) {
                                    Text(stringResource(R.string.ok))
                                }
                            })
                    }



            }
        }
    )
}

