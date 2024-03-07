package com.example.madtwoassignmentone.views.role



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madtwoassignmentone.models.RoleModel
import com.example.madtwoassignmentone.models.RoleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class RoleViewModel(private val roleRepository: RoleRepository): ViewModel() {


    val homeUiState: StateFlow<HomeUiState> =
        roleRepository.findAll().map { HomeUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HomeUiState()

            )


    companion object {
        private const val TIMEOUT_MILLIS = 5_000L

    }

    fun getName(name: String): Flow<List<RoleModel>> {
        return roleRepository.findOneName(name)
    }

    fun getWinRate(winRate: Int): Flow<List<RoleModel>> {
        return roleRepository.findWinRateAbove(winRate)
    }




}




/**
 * Ui State for HomeScreen
 */
data class HomeUiState(val roleList: List<RoleModel> = listOf())