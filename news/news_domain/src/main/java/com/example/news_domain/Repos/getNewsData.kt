package com.example.news_domain.Repos

import com.example.news_domain.model.NewsData

interface getNewsData {
    suspend fun getData() : NewsData
    suspend fun getSingleNews(articleId : String) : NewsData
}