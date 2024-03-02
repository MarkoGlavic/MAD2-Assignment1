package com.example.madtwoassignmentone.views.role

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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.madtwoassignmentone.models.ChampionModel
import com.example.madtwoassignmentone.models.IdGenerator
import com.example.madtwoassignmentone.navigation.NavigationDestination
import com.example.madtwoassignmentone.R
import com.example.madtwoassignmentone.ViewModelProvider
import com.example.madtwoassignmentone.models.RoleModel


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

                          Scaffold(
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

                var description by remember { mutableStateOf("") }

                OutlinedTextField(
                    value = description,
                    onValueChange = {description = it} )


                Button(
                    onClick = {
                        val role = RoleModel(IdGenerator.generateId(), championName, description)
                        viewModel.addRole(role)
                        navigateBack()
                    }
                ) {
                    Text(text = "Add Champion")
                }
            }
        }
    )
}
