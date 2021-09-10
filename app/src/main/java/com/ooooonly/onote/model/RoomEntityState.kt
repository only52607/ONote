package com.ooooonly.onote.model

import com.ooooonly.onote.model.entity.RoomEntity

interface RoomEntityState<T: RoomEntity> {
    val entity: T
    suspend fun save() {}
}