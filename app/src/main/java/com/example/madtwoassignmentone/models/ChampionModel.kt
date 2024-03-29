package com.example.madtwoassignmentone.models


data class ChampionModel(
    var id: Long = 0,
    var roleId : Long = 0,
    var name: String = "",
    var winRate: Int = 0
)


object IdGenerator {
    private var nextId: Long = 0

    fun generateId(): Long {
        return nextId++
    }
}

