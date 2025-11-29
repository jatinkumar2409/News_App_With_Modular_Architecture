package com.example.presentation.newsScreens.details

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news_domain.Repos.getNewsData
import com.example.news_domain.useCases.getSingleNews
import com.example.utils.model.NewsDTO
import kotlinx.coroutines.launch

class DetailsScreenViewModel(private val getSingleNews : getSingleNews) : ViewModel() {
    val news = mutableStateOf<NewsDTO?>(null)
    var isLoading = mutableStateOf(true)
    fun getNewsData(id : String){
        isLoading.value = true
        viewModelScope.launch {
            try {
                news.value = getSingleNews.getSingleNews(id)
                isLoading.value = false
            }catch (e : Exception){
                Log.d("tag1", "getNewsData: $e")
                news.value = NewsDTO(title = "Data not found")
                isLoading.value = false
            }

        }
    }
}