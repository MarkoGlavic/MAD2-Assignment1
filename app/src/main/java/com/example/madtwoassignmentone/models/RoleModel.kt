package com.example.madtwoassignmentone.models


data class RoleModel(
    var id: Long = IdGenerator.generateId(),
    var name: String ="",
    var description: String="",
    var winRate: Int = 0
)



