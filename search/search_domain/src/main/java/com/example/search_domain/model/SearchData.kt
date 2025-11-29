package com.example.search_domain.model

import kotlinx.serialization.Serializable

@Serializable
data class SearchData(
    val nextPage: String? = null,
    val results: List<Result> = emptyList(),
    val status: String? = null,
    val totalResults: Int? = null
)