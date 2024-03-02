package com.example.madtwoassignmentone.models


data class RoleModel(
    var id: Long = 0,
    var name: String ="",
    var description: String="",
    var roleChampions: ArrayList<ChampionModel> = ArrayList<ChampionModel>()

)



