package com.example.search_data.impls

import com.example.search_domain.model.SearchData
import com.example.search_domain.repos.getSearchData
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.URLProtocol
import io.ktor.http.encodedPath
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class searchDataImpl : getSearchData {
    fun getClient() = HttpClient {
        install(ContentNegotiation){
            json(Json{
                ignoreUnknownKeys = true
                coerceInputValues = true
            })
        }
        install(DefaultRequest){
            url {
                protocol = URLProtocol.HTTPS
                host = "newsdata.io"
                encodedPath = "/api/1/latest"
                parameters.append("apikey", "pub_47cdf7e3bd724d2bab791986a1e1ccab")

            }
        }
    }
    override suspend fun getData(topic: String): SearchData {
       return getClient().get {
            parameter("q" , topic)
       }.body<SearchData>()
    }

    override suspend fun getSingleData(id: String): SearchData {
        return getClient().get(){
            parameter("id" , id)
        }.body()
    }
}