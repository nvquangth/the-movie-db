package com.example.cleanarchitecture.data.model

import com.example.cleanarchitecture.entity.Model

interface EntityMapper<M : Model, ME : ModelEntity> {

    /**
     * convert data from the format of use cases and entities layer, to the format of data layer
     */
    fun mapToData(model: M): ME

    /**
     * convert data from the format of data layer, to the format of use cases and entities layer
     */
    fun mapToDomain(entity: ME): M
}
