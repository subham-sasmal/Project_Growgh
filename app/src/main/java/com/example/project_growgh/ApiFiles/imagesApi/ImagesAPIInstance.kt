package com.example.project_growgh.ApiFiles.imagesApi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ImagesAPIInstance {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getImagesApiInstance(): Retrofit {
        return retrofit
    }
}