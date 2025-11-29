package com.example.news_domain.utils


import com.example.news_domain.model.NewsData
import com.example.utils.model.NewsDTO
import kotlin.map

fun NewsData.toNewsDTOs() : List<NewsDTO>{
    val results = this.results
    return results.map { it ->
        NewsDTO(title = it?.title ?: "", description = it?.description ?: "" , imageUrl = it.image_url , language = it.language , link = it?.link ?: ""  , articleId = it?.article_id ?: "")
    }
}