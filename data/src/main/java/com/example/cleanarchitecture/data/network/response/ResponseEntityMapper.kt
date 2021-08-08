package com.example.cleanarchitecture.data.network.response

import com.example.cleanarchitecture.data.model.ModelEntity

interface ResponseEntityMapper<ME : ModelEntity, RE : ResponseEntity> {

    fun mapToModelEntity(responseEntity: RE): ME

    fun mapToResponseEntity(modelEntity: ME): RE
}
