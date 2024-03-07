package com.example.madtwoassignmentone.models

import kotlinx.coroutines.flow.Flow

interface RoleStore {
    fun findAll(): Flow<List<RoleModel>>
    fun findOne(id: Long): RoleModel?
    fun create(role : RoleModel)
    fun delete(role : RoleModel)


    fun getItemStream(long: Long): Flow<RoleModel?>

}