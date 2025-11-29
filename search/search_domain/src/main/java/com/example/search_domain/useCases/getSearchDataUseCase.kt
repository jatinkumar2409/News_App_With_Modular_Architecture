package com.example.search_domain.useCases

import com.example.search_domain.model.SearchData
import com.example.search_domain.repos.getSearchData
import com.example.utils.model.NewsDTO

class getSearchDataUseCase(private val getSearchData : getSearchData) {
    suspend fun getData(topic : String) : List<NewsDTO>{
        return getSearchData.getData(topic).toNewsDTO()
    }

}

fun SearchData.toNewsDTO() : List<NewsDTO>{
    val results = this.results
    return results.map { it ->
        NewsDTO(title = it?.title ?: "", description = it?.description ?: "" , imageUrl = it.image_url , language = it.language , link = it?.link ?: ""  , articleId = it?.article_id ?: "")
    }
}