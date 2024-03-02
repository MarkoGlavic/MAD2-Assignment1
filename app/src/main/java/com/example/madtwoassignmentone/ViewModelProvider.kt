package com.example.madtwoassignmentone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.madtwoassignmentone.models.ChampionRepository
import com.example.madtwoassignmentone.views.home.HomeViewModel

class HomeViewModelFactory(private val championRepository: ChampionRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(championRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}