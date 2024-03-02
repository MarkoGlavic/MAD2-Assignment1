package com.example.madtwoassignmentone

import android.app.Application
import com.example.madtwoassignmentone.models.ChampionRepository
import com.example.madtwoassignmentone.models.RoleRepository

class MyApplication : Application() {
    lateinit var championRepository: ChampionRepository
    lateinit var roleRepository: RoleRepository


    override fun onCreate() {
        super.onCreate()
        championRepository = ChampionRepository()
        roleRepository = RoleRepository()

    }
}