package com.example.madtwoassignmentone

import android.app.Application
import com.example.madtwoassignmentone.models.ChampionRepository

class MyApplication : Application() {
    lateinit var championRepository: ChampionRepository

    override fun onCreate() {
        super.onCreate()
        championRepository = ChampionRepository()

    }
}