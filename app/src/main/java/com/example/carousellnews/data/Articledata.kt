package com.example.carousellnews.data

import com.squareup.moshi.Json

data class Data(
    val id: String,
    val title: String,
    val description: String,
    @Json(name = "banner_url") val image: String,
    @Json(name = "time_created") val time: Long,
    val rank: Int
)

