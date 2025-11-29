package com.example.search_domain.repos

import com.example.search_domain.model.SearchData

interface getSearchData {
    suspend fun getData(topic : String) : SearchData
    suspend fun getSingleData(id : String) : SearchData
}