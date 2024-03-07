package com.example.madtwoassignmentone.views.role

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madtwoassignmentone.models.RoleRepository
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class RoleEditModel(savedStateHandle: SavedStateHandle,
    private val roleRepository: RoleRepository) : ViewModel(
    ) {
    var roleUiState by mutableStateOf(RoleUiState())
        private set

    var roleDetailsUiState by mutableStateOf(RoleDetailsUiState())
        private set



    private val roleId: Long = checkNotNull(savedStateHandle
        [RoleEditDestination.roleIdArg])


    init {
        viewModelScope.launch {
            roleUiState = roleRepository.getItemStream(roleId)
                .filterNotNull()
                .first()
                .toRoleUiState(true)
        }
    }



    fun updateUiState(roleDetails: RoleDetails) {
        roleUiState =
            RoleUiState(roleDetails = roleDetails, isEntryValid = validateInput(roleDetails))
    }

    private fun validateInput(uiState: RoleDetails = roleUiState.roleDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && description.isNotBlank() && winRate!= 0
        }

    }

    suspend fun updateRole() {
        if (validateInput(roleUiState.roleDetails)) {
            roleRepository.update(roleUiState.roleDetails.toRole())
        }
    }

}

