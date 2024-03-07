package com.example.madtwoassignmentone.models

import android.content.Context
import android.net.Uri
import com.example.madtwoassignmentone.helpers.exists
import com.example.madtwoassignmentone.helpers.read
import com.example.madtwoassignmentone.helpers.write
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.lang.reflect.Type
import java.util.ArrayList



class RoleRepository(private val context: Context) : RoleStore {
    private val JSON_FILE = "roles.json"
    private val gson = Gson()

    private val _roles = MutableStateFlow<List<RoleModel>>(emptyList())
    val roles: StateFlow<List<RoleModel>> = _roles

    init {
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    private fun serialize() {
        val jsonString = gson.toJson(_roles.value)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        _roles.value = gson.fromJson(jsonString, object : TypeToken<List<RoleModel>>() {}.type)
    }

    override fun findAll(): Flow<List<RoleModel>> {
        return _roles

    }

    override fun findOne(id: Long): RoleModel? {
        return _roles.value.find { it.id == id }
    }

    override fun create(role: RoleModel) {
        _roles.value += role
        serialize()

    }

    override fun getItemStream(long: Long): Flow<RoleModel?> {
        return flow {
            emit(_roles.value[long.toInt()])
        }
    }

    override fun delete(role: RoleModel) {
        _roles.value = _roles.value.filterNot { it == role }

        _roles.value = _roles.value.mapIndexed { index, existingRole ->
            if (existingRole.id != role.id) {
                existingRole.copy(id = index.toLong())
            } else {
                existingRole
            }
        }
        serialize()


    }

    fun update(role: RoleModel) {
        _roles.value = _roles.value.map { existingRole ->
            if (existingRole.id == role.id) {
                role.copy(name = existingRole.name)
                role.copy(description = existingRole.description)
                role.copy(winRate = existingRole.winRate)
            } else {
                existingRole
            }
        }
        serialize()

    }


    override fun findOneName(string: String): Flow<List<RoleModel>> {
        return _roles.map { roleList ->
            roleList.filter { it.name.contains(string, ignoreCase = true) }
        }
    }

    override fun findWinRateAbove(winRate: Int): Flow<List<RoleModel>> {
        return _roles.map { roleList ->
            roleList.filter { it.winRate >= winRate }
        }
    }
}

object RoleRepositoryProvider {

    private lateinit var context: Context

    fun initialize(context: Context) {
        this.context = context
    }

    private val roleRepository: RoleRepository by lazy { RoleRepository(context) }

    fun provideRoleRepository(): RoleRepository {
        return roleRepository
    }
}

