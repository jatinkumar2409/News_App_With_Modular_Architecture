package com.example.news_data.model

import kotlinx.serialization.Serializable

@Serializable
data class NewsData(
    val nextPage: String? = null,
    val results: List<Result> = emptyList(),
    val status: String? = null,
    val totalResults: Int? = null
)