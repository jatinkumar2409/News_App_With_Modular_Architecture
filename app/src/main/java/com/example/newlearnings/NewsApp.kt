package com.example.newlearnings

import android.app.Application
import com.example.news_data.newsDataModule
import com.example.news_domain.utils.newsDomainModule
import com.example.presentation.koin.newsUiMoudule
import com.example.search_data.koin.searchModule
import com.example.search_presentation.Screens.koin.searchUiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NewsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NewsApp)
            modules(listOf(newsDataModule , newsDomainModule , newsUiMoudule , searchModule ,
                searchUiModule
            ))
        }
    }
}