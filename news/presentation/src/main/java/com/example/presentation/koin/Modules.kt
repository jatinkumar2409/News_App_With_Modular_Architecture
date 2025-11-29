package com.example.presentation.koin

import com.example.presentation.newsScreens.details.DetailsScreenViewModel
import com.example.presentation.newsScreens.home.HomeScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val newsUiMoudule = module {
 viewModel {
     HomeScreenViewModel(get())
 }
    viewModel {
        DetailsScreenViewModel(get())

    }
}