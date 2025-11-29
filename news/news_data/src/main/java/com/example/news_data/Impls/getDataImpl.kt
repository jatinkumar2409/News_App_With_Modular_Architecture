package com.example.news_data.Impls

import com.example.news_data.model.NewsData
import com.example.news_domain.Repos.getNewsData
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

class getDataImpl : getNewsData{
    fun getClient() = HttpClient{
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    coerceInputValues = true
                })
            }
            install(DefaultRequest) {
                url {
                    protocol = URLProtocol.HTTPS
                    host = "newsdata.io"
                    encodedPath = "/api/1/latest"
                    parameters.append("apikey", "pub_47cdf7e3bd724d2bab791986a1e1ccab")
                }
            }

    }
    override suspend fun getData(): com.example.news_domain.model.NewsData {
      return getClient().get {}.body()
    }
    override suspend fun getSingleNews(articleId : String): com.example.news_domain.model.NewsData {
        return getClient().get(){
     parameter("id" , articleId)
        }.body()
    }
}