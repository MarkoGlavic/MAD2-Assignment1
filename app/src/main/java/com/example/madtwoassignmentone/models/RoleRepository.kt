package com.example.madtwoassignmentone.models

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow


class RoleRepository : RoleStore {
    private val roles = MutableStateFlow<List<RoleModel>>(emptyList())

    override fun findAll(): Flow<List<RoleModel>> {
        return roles
    }

    override fun findOne(id: Long): RoleModel? {
        return roles.value.find { it.id == id }
    }

    override fun create(role: RoleModel) {
        roles.value += role
    }

    override fun getItemStream(long: Long): Flow<RoleModel?> {
        return flow {
            // Emit the current value of roles
            emit(roles.value[long.toInt()])}
        }
}

object RoleRepositoryProvider {
    private val roleRepository: RoleRepository by lazy { RoleRepository() }

    fun provideRoleRepository(): RoleRepository {
        return roleRepository
    }
}