package com.example.madtwoassignmentone.models
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow


class ChampionRepository : ChampionStore {
    private val champions = MutableStateFlow<List<ChampionModel>>(emptyList())

    override fun findAll(): Flow<List<ChampionModel>> {
        return champions
    }

    override fun findOne(id: Long): ChampionModel? {
        return champions.value.find { it.id == id  }
    }

    override fun create(champion: ChampionModel) {
        champions.value += champion
    }

    override fun getChampionsForRole(roleId: Long): Flow<List<ChampionModel>> {
        val championsForRole = champions.value.filter { it.id == roleId }
        return MutableStateFlow(championsForRole)

    }
}

object ChampionRepositoryProvider {
    private val championRepository: ChampionRepository by lazy { ChampionRepository() }

    fun provideChampionRepository(): ChampionRepository {
        return championRepository
    }
}