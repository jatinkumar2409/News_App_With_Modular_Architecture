package com.example.news_domain.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class Result(
val article_id: String? = null,
val link: String? = null,
val title: String? = null,
val description: String? = null,
val content: String? = null,
val keywords: List<String>? = null,
val creator: List<String>? = null,
val language: String? = null,
val country: List<String>? = null,
val category: List<String>? = null,
val datatype: String? = null,
val pubDate: String? = null,
val pubDateTZ: String? = null,
val image_url: String? = null,
val video_url: String? = null,
val source_id: String? = null,
val source_name: String? = null,
val source_priority: Int? = null,
val source_url: String? = null,
val source_icon: String? = null,
val sentiment: String? = null,
val sentiment_stats: String? = null,
val ai_tag: String? = null,
val ai_region: String? = null,
val ai_org: String? = null,
val ai_summary: String? = null,
val duplicate: Boolean? = null
)
