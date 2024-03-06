package com.example.madtwoassignmentone.views.role


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madtwoassignmentone.models.RoleModel
import com.example.madtwoassignmentone.models.RoleRepository
import kotlinx.coroutines.launch

class RoleAddModel(
    private val roleRepository: RoleRepository
): ViewModel(){
    fun addRole(role: RoleModel){
        viewModelScope.launch {
            roleRepository.create(role)
        }
    }
}


data class RoleDetails(
    val id: Long = 0,
    val name: String = "",
    val description: String = ""
)

fun RoleDetails.toItem(): RoleModel = RoleModel(
    id = id,
    name = name,
    description = description,
)

fun RoleModel.roleDetails(): RoleDetails = RoleDetails(
    id = id,
    name = name,
    description = description,
)





data class RoleAddUiState(val roleList: List<RoleModel> = listOf())