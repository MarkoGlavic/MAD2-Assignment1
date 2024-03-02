package com.example.madtwoassignmentone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.madtwoassignmentone.models.ChampionRepository
import com.example.madtwoassignmentone.models.ChampionRepositoryProvider
import com.example.madtwoassignmentone.models.RoleRepository
import com.example.madtwoassignmentone.models.RoleRepositoryProvider
import com.example.madtwoassignmentone.views.home.HomeAddModel
import com.example.madtwoassignmentone.views.home.HomeViewModel
import com.example.madtwoassignmentone.views.role.RoleAddModel
import com.example.madtwoassignmentone.views.role.RoleViewModel

class HomeViewModelFactory(private val championRepository: ChampionRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(championRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class HomeAddViewModelFactory(private val championRepository: ChampionRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeAddModel::class.java)) {
            return HomeAddModel(championRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class RoleScreenFactory(private val roleRepository: RoleRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RoleViewModel::class.java)) {
            return RoleViewModel(roleRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
class RoleAddFactory(private val roleRepository: RoleRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RoleAddModel::class.java)) {
            return RoleAddModel(roleRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


class ViewModelProvider {
    private val championRepository = ChampionRepositoryProvider.provideChampionRepository()
    private val roleRepository = RoleRepositoryProvider.provideRoleRepository()

    val homeViewModelFactory = HomeViewModelFactory(championRepository)
    val homeAddViewModelFactory = HomeAddViewModelFactory(championRepository)
    val roleViewModelFactory = RoleScreenFactory(roleRepository)
    val roleAddFactory= RoleAddFactory(roleRepository)

}

