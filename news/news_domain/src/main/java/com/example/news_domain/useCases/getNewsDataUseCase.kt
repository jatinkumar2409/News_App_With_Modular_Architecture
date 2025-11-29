package com.example.news_domain.useCases

import com.example.news_domain.Repos.getNewsData
import com.example.news_domain.utils.toNewsDTOs
import com.example.utils.model.NewsDTO


class getNewsDataUseCase(private val newsData: getNewsData) {
     suspend fun getNewsData() : List<NewsDTO>{
         return newsData.getData().toNewsDTOs()
    }
}