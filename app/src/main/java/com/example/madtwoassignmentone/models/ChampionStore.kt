package com.example.madtwoassignmentone.models

import kotlinx.coroutines.flow.Flow

interface ChampionStore {
    fun findAll(): Flow<List<ChampionModel>>
    fun findOne(id: Long): ChampionModel?
    fun create(champion : ChampionModel)
    fun getChampionsForRole(roleId: Long): Flow<List<ChampionModel>>


}