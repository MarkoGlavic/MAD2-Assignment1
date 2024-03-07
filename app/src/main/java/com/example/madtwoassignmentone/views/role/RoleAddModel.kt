package com.example.madtwoassignmentone.views.role


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madtwoassignmentone.models.IdGenerator
import com.example.madtwoassignmentone.models.RoleModel
import com.example.madtwoassignmentone.models.RoleRepository
import kotlinx.coroutines.launch

class RoleAddModel(
    private val roleRepository: RoleRepository
): ViewModel(){
    var roleUiState by mutableStateOf(RoleUiState())
        private set

    fun updateUiState(roleDetails: RoleDetails) {
        roleUiState =
            RoleUiState(roleDetails = roleDetails, isEntryValid = validateInput(roleDetails))
    }

    private fun validateInput(uiState: RoleDetails = roleUiState.roleDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && description.isNotBlank() && winRate != 0
        }
    }

    suspend fun saveItem() {
        if (validateInput()) {
            roleRepository.create(roleUiState.roleDetails.toRole())
        }
    }

}

data class RoleUiState(
    val roleDetails: RoleDetails = RoleDetails(),
    val isEntryValid: Boolean = false
)

data class RoleDetails(
    val id: Long = IdGenerator.generateId(),
    val name: String = "",
    val description: String = "",
    val winRate : Int = 0
)


fun RoleDetails.toRole(): RoleModel = RoleModel(
    id = id,
    name = name,
    description = description,
    winRate = winRate,
)




fun RoleModel.toRoleUiState(isEntryValid: Boolean = false): RoleUiState = RoleUiState(
    roleDetails = this.toRoleDetails(),
    isEntryValid = isEntryValid
)


fun RoleModel.toRoleDetails(): RoleDetails = RoleDetails(
    id = id,
    name = name,
    description = description,
    winRate = winRate
)



