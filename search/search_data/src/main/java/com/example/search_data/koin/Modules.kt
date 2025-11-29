package com.example.search_data.koin

import com.example.search_data.impls.searchDataImpl
import com.example.search_domain.repos.getSearchData
import com.example.search_domain.useCases.getSearchDataUseCase
import com.example.search_domain.useCases.getSingleData
import org.koin.dsl.module

val searchModule = module {
    single<getSearchData> {
        searchDataImpl()
    }
    single {
        getSearchDataUseCase(get())
    }
    single {
        getSingleData(get())
    }
}