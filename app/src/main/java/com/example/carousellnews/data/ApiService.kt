package com.example.carousellnews.data

import retrofit2.http.GET

interface Api {
    @GET("android/carousell_news.json")
    suspend fun getNews(): List<Data>
}