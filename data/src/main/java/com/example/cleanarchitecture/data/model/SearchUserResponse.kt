package com.example.data.model

import com.example.cleanarchitecture.data.model.UserEntity
import com.google.gson.annotations.SerializedName

data class SearchUserResponse(
    @SerializedName("total_count")
    val totalCount: Int? = null,
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean? = null,
    @SerializedName("items")
    val items: List<UserEntity>? = null
)
