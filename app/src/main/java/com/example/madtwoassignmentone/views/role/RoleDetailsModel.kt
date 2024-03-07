package com.example.madtwoassignmentone.views.role

import androidx.compose.ui.semantics.Role
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madtwoassignmentone.models.RoleRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class RoleDetailsModel(
    savedStateHandle: SavedStateHandle,
    private val roleRepository: RoleRepository
    ) : ViewModel(){
    private val roleId: Long = checkNotNull(savedStateHandle[RoleDetailsDestination.roleIdArg])

    val uiState: StateFlow<RoleDetailsUiState> = roleRepository.getItemStream(roleId)
        .filterNotNull()
        .map { RoleDetailsUiState(roleDetails = it.toRoleDetails()) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = RoleDetailsUiState()
        )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    suspend fun deleteRole() {
        roleRepository.delete(uiState.value.roleDetails.toRole())
    }


}





data class RoleDetailsUiState(
    val roleDetails: RoleDetails = RoleDetails(),
)