package com.example.madtwoassignmentone.views.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madtwoassignmentone.models.ChampionModel
import com.example.madtwoassignmentone.models.ChampionRepository
import com.example.madtwoassignmentone.models.IdGenerator
import com.example.madtwoassignmentone.models.RoleRepository
import com.example.madtwoassignmentone.views.role.RoleDetails
import com.example.madtwoassignmentone.views.role.RoleUiState
import com.example.madtwoassignmentone.views.role.toRoleDetails
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import com.example.madtwoassignmentone.views.role.toRoleUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeAddModel(
    savedStateHandle: SavedStateHandle,
    private val roleRepository: RoleRepository,
private val championRepository: ChampionRepository) : ViewModel() {



    private val roleId: Long = checkNotNull(savedStateHandle[HomeDestination.championId])


    val uiState: StateFlow<ChampionRoleUiState> =
        roleRepository.getItemStream(roleId)
            .filterNotNull()
            .map {
                ChampionRoleUiState(roleDetails = it.toRoleDetails())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(HomeViewModel.TIMEOUT_MILLIS),
                initialValue = ChampionRoleUiState()
            )

    val champions: StateFlow<List<ChampionModel>> =
        championRepository.getChampionsForRole(roleId)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(HomeViewModel.TIMEOUT_MILLIS),
                initialValue = emptyList()
            )

    companion object {
         const val TIMEOUT_MILLIS = 5_000L
    }

    var championUiState by mutableStateOf(ChampionUiState())
        private set


    fun updateChampionState(championDetails: ChampionDetails) {
             championUiState= ChampionUiState(
                championDetails = championDetails,
                isEntryValid = validateInput(championDetails)
            )
        }

    private fun validateInput(uiState: ChampionDetails = championUiState.championDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && winRate != 0
        }
    }

    suspend fun saveChampion() {
        if (validateInput()) {
            championRepository.create(championUiState.championDetails.toChampion())
        }
    }

}



data class ChampionUiState(
    val championDetails: ChampionDetails = ChampionDetails(),
    val isEntryValid: Boolean = false
)

data class ChampionDetails(
    val id: Long = 0,
    val roleId: Long = 0,
    val name: String = "",
    val winRate:Int = 0
)
fun ChampionDetails.toChampion(): ChampionModel = ChampionModel(
    id = id,
    roleId = roleId,
    name = name,
    winRate = winRate
)


/**
 * Ui State for HomeScreen
 */
data class ChampionRoleUiState(
    val roleDetails: RoleDetails = RoleDetails(),
    val championDetails: ChampionDetails = ChampionDetails(),
    val championList: List<ChampionModel> = listOf()
)

