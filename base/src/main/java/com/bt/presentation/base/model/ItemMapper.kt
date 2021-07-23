package com.bt.presentation.base.model

import com.example.cleanarchitecture.entity.Model

interface ItemMapper<M : Model, MI : ModelItem> {

    /**
     * convert data from the format of use cases and entities layer, to the format of presentation
     * layer
     */
    fun mapToPresentation(model: M): MI

    /**
     * convert data from the format of presentation layer, to the format of use cases and entities
     * layer
     */
    fun mapToDomain(item: MI): M
}
