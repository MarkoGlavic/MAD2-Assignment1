package com.example.madtwoassignmentone.models

import kotlinx.coroutines.flow.Flow

interface RoleStore {
    fun findAll(): Flow<List<RoleModel>>
    fun findOne(id: Long): RoleModel?
    fun create(role : RoleModel)
    fun delete(role : RoleModel)

    fun findOneName(string:String) :Flow<List<RoleModel>>

    fun findWinRateAbove(int:Int): Flow<List<RoleModel>>

    fun getItemStream(long: Long): Flow<RoleModel?>

}