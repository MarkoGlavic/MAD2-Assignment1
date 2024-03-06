package com.example.madtwoassignmentone.models

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow


var lastId = 0L

internal fun getId(): Long {
    return lastId++
}
