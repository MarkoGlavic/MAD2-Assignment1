package com.example.madtwoassignmentone.views.role



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madtwoassignmentone.models.RoleModel
import com.example.madtwoassignmentone.models.RoleRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class RoleViewModel(private val roleRepository: RoleRepository): ViewModel() {


    val roleUiState: StateFlow<RoleUiState> =
        roleRepository.findAll().map { RoleUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = RoleUiState()

            )


    companion object {
        private const val TIMEOUT_MILLIS = 5_000L

    }

    fun getRoleById(id: Long): RoleModel? {
        return roleRepository.findOne(id)
    }

    fun deleteRole(roleId: Long) {
        roleRepository.delete(roleId)
    }

}




/**
 * Ui State for HomeScreen
 */
data class RoleUiState(val roleList: List<RoleModel> = listOf())