package com.example.news.di

import com.example.carousellnews.data.Api
import com.example.carousellnews.data.Repository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun moshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Provides
    @Singleton
    fun okHttp(): OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    @Singleton
    fun retrofit(moshi: Moshi, okHttp: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("https://storage.googleapis.com/carousell-interview-assets/")
        .client(okHttp)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Provides
    @Singleton
    fun api(retrofit: Retrofit): Api = retrofit.create(Api::class.java)

    @Provides
    @Singleton
    fun repo(api: Api): Repository = Repository(api)
}