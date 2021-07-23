package com.example.cleanarchitecture.data.model

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    val page: Int? = null,
    @SerializedName("total_results")
    val totalResults: Int? = null,
    @SerializedName("total_pages")
    val totalPages: Int? = null,
    val results: List<MovieEntity>? = null
)
