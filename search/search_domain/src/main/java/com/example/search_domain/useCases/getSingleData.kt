package com.example.search_domain.useCases

import com.example.search_domain.repos.getSearchData
import com.example.utils.model.NewsDTO

class getSingleData(private val getSearchData : getSearchData) {
    suspend fun getSingleData(id : String) : NewsDTO{
        return getSearchData.getSingleData(id).toNewsDTO().first()
    }
}