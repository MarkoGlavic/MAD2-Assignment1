package com.example.madtwoassignmentone

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.madtwoassignmentone.models.ChampionRepository
import com.example.madtwoassignmentone.models.ChampionRepositoryProvider
import com.example.madtwoassignmentone.models.RoleRepository
import com.example.madtwoassignmentone.models.RoleRepositoryProvider
import com.example.madtwoassignmentone.views.role.RoleAddModel
import com.example.madtwoassignmentone.views.role.RoleDetailsModel
import com.example.madtwoassignmentone.views.role.RoleViewModel
object AppViewModelProvider {
    private val roleRepository = RoleRepositoryProvider.provideRoleRepository()

    val Factory = viewModelFactory {
        initializer {
            RoleViewModel(roleRepository)
        }
        initializer {
            RoleAddModel(roleRepository)
        }
        initializer {
            RoleDetailsModel(this.createSavedStateHandle(),roleRepository)
        }


    }

}
