package com.example.carousellnews.data

import javax.inject.Inject

class Repository @Inject constructor(
    private val api : Api
){
    suspend fun fetchArticles(): List<Data> = api.getNews()
}
