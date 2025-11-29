package com.example.news_domain.utils

import com.example.news_domain.useCases.getNewsDataUseCase
import com.example.news_domain.useCases.getSingleNews
import org.koin.dsl.module

val newsDomainModule = module {
    single<getNewsDataUseCase> {
        getNewsDataUseCase(get())
    }
    single {
        getSingleNews(get())
    }
}