package com.example.cleanarchitecture.data.database.room.entity

import com.example.cleanarchitecture.data.model.ModelEntity

interface RoomEntityMapper<ME : ModelEntity, RE : RoomEntity> {

    fun mapToModelEntity(roomEntity: RE): ME

    fun mapToRoomEntity(modelEntity: ME): RE
}
