package com.example.madtwoassignmentone

import android.app.Application
import com.example.madtwoassignmentone.models.ChampionRepository
import com.example.madtwoassignmentone.models.RoleRepository
import com.example.madtwoassignmentone.models.RoleRepositoryProvider

class MyApplication : Application() {



    override fun onCreate() {
        super.onCreate()
        RoleRepositoryProvider.initialize(this)

    }
}