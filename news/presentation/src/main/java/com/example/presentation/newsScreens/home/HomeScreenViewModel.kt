package com.example.presentation.newsScreens.home

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news_domain.useCases.getNewsDataUseCase
import com.example.utils.model.NewsDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel(private val getNewsData : getNewsDataUseCase) : ViewModel() {
    private val newsList_ = MutableStateFlow<List<NewsDTO>>(emptyList())
    val newsList = newsList_.asStateFlow()
    var isLoading = mutableStateOf(true)
    init {
        getNewsData()
    }
    fun getNewsData(){
        viewModelScope.launch(Dispatchers.IO){
            try {
                newsList_.value = getNewsData.getNewsData()
                isLoading.value = false
            }catch (e : Exception){
                Log.d("tag" , e.toString())
                newsList_.value = listOf(NewsDTO(title = "data not found" , description = "" , imageUrl = "" , language = "" , link = ""))
                isLoading.value = false
            }
        }
    }
}