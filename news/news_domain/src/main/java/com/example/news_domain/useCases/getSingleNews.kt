package com.example.news_domain.useCases

import com.example.news_domain.Repos.getNewsData
import com.example.news_domain.utils.toNewsDTOs
import com.example.utils.model.NewsDTO

class getSingleNews(private val newsData : getNewsData) {
    suspend fun getSingleNews(artcleId : String) : NewsDTO{
        return newsData.getSingleNews(artcleId).toNewsDTOs().first()
    }
}