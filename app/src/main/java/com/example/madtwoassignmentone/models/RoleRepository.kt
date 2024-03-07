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
            emit(roles.value[long.toInt()])}
        }

    override fun delete(role: RoleModel) {
        roles.value = roles.value.filterNot { it == role }

        roles.value = roles.value.mapIndexed { index, existingRole ->
            if (existingRole.id != role.id) {
                existingRole.copy(id = index.toLong())
            } else {
                existingRole
            }
        }

    }

    fun update(role: RoleModel) {
        roles.value = roles.value.map { existingRole ->
            if (existingRole.id == role.id) {
                role.copy(name = existingRole.name)
                role.copy(description = existingRole.description)
                role.copy(winRate = existingRole.winRate)
            } else {
                existingRole
            }
        }
    }


}

object RoleRepositoryProvider {
    private val roleRepository: RoleRepository by lazy { RoleRepository() }

    fun provideRoleRepository(): RoleRepository {
        return roleRepository
    }
}

object IdGeneratorChampion {
    private var nextId: Long = 0

    fun generateId(): Long {
        return nextId++
    }
}