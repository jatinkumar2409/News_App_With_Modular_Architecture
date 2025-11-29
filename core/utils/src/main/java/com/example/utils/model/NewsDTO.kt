package com.example.utils.model

import kotlinx.serialization.Serializable

@Serializable
data class NewsDTO(
    val title: String = "",
    val description: String = "",
    val imageUrl: String? = null,
    val language: String? = null,
    val link: String = "" ,
    val articleId : String = ""
)

//https://newsdata.io/api/1/latest?apikey=pub_47cdf7e3bd724d2bab791986a1e1ccab&q=sports