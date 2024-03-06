package com.example.madtwoassignmentone.views.home


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madtwoassignmentone.models.ChampionModel
import com.example.madtwoassignmentone.models.ChampionRepository
import com.example.madtwoassignmentone.models.RoleRepository
import com.example.madtwoassignmentone.views.role.RoleDetails
import com.example.madtwoassignmentone.views.role.toRoleDetails

import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(savedStateHandle: SavedStateHandle, private val roleRepository: RoleRepository, private val championRepository: ChampionRepository): ViewModel() {


    private val roleId: Long = savedStateHandle.get<Long>(HomeDestination.championId)
        ?: throw IllegalStateException("Champion ID not found in SavedStateHandle")

    val uiState: StateFlow<ChampionRoleUiState> =
        roleRepository.getItemStream(roleId)
            .filterNotNull()
            .map {
                ChampionRoleUiState(roleDetails = it.toRoleDetails())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = ChampionRoleUiState()
            )

    val champions: StateFlow<List<ChampionModel>> =
        championRepository.getChampionsForRole(roleId)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = emptyList()
            )


    companion object {
        const val TIMEOUT_MILLIS = 5_000L

    }




}

