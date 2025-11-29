package com.example.search_presentation.Screens.screens.search

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.search_domain.useCases.getSearchDataUseCase
import com.example.utils.model.NewsDTO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchScreenViewModel(private val getSearchData : getSearchDataUseCase) : ViewModel(){
  private  val searchList_ = MutableStateFlow<List<NewsDTO>>(emptyList())
    val searchList = searchList_.asStateFlow()
    val isLoading = mutableStateOf(false)
    fun getSearchData(topic : String){
        isLoading.value = true
        viewModelScope.launch {
            try {
                searchList_.value = getSearchData.getData(topic)
                if (searchList_.value.isEmpty())  searchList_.value = listOf(NewsDTO())
                isLoading.value = false
            }catch (e : Exception){
                Log.d("tagw" , e.toString())
                searchList_.value = listOf(NewsDTO())
                isLoading.value = false
            }
        }
    }

}