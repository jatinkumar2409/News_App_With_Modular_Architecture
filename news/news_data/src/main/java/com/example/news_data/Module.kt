package com.example.news_data

import com.example.news_data.Impls.getDataImpl
import com.example.news_domain.Repos.getNewsData
import org.koin.dsl.module

val newsDataModule = module {
    single<getNewsData> {
        getDataImpl()
    }
}