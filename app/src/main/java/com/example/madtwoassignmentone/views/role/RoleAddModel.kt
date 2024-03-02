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


data class RoleAddUiState(val roleList: List<RoleModel> = listOf())