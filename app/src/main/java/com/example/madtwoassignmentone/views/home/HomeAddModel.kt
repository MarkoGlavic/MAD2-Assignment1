package com.example.madtwoassignmentone.views.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madtwoassignmentone.models.ChampionModel
import com.example.madtwoassignmentone.models.ChampionRepository
import kotlinx.coroutines.launch

class HomeAddModel(
    private val championRepository: ChampionRepository
): ViewModel(){
    fun addChampion(champion:ChampionModel){
        viewModelScope.launch {
            championRepository.create(champion)
        }
    }
}


data class HomeAddUiState(val championList: List<ChampionModel> = listOf())