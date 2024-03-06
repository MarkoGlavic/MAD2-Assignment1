package com.example.madtwoassignmentone.views.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madtwoassignmentone.models.ChampionModel
import com.example.madtwoassignmentone.models.ChampionRepository
import com.example.madtwoassignmentone.models.ChampionStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(private val championRepository: ChampionRepository): ViewModel() {


    val homeUiState: StateFlow<HomeUiState> =
        championRepository.findAll().map { HomeUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HomeUiState()
            )


    companion object {
        private const val TIMEOUT_MILLIS = 5_000L

    }

    fun getChampionById(id: Long): ChampionModel? {
        return championRepository.findOne(id)
    }



}


/**
 * Ui State for HomeScreen
 */
data class HomeUiState(val championList: List<ChampionModel> = listOf())