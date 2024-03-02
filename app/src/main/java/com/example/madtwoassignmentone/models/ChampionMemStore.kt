package com.example.madtwoassignmentone.models

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow


var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class ChampionMemStore : ChampionStore {

    val champions = ArrayList<ChampionModel>()

    private val _championsFlow = MutableStateFlow<List<ChampionModel>>(emptyList())
    override fun findAll(): Flow<List<ChampionModel>> {
        return _championsFlow
    }

    override fun findOne(id: Long): ChampionModel? {
        return champions.find { it.id == id }
    }

    override fun create(champion: ChampionModel) {
        champion.id = getId()
        champions.add(champion)
        _championsFlow.value = champions.toList()
    }
}