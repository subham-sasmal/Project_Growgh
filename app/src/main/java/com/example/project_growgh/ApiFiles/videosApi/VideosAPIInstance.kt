package com.example.project_growgh.ApiFiles.videosApi

import com.example.project_growgh.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object VideosAPIInstance {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getVideoApiInstance(): Retrofit {
        return retrofit
    }
}