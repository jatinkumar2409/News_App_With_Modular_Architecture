package com.example.search_presentation.Screens.koin

import com.example.search_presentation.Screens.screens.search.SearchScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val searchUiModule = module {
   viewModel {
       SearchScreenViewModel(get())
   }
}