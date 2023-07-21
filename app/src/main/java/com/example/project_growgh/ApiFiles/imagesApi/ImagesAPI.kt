package com.example.project_growgh.ApiFiles.imagesApi

import com.example.project_growgh.ApiFiles.imagesApi.jsontokotlinfiles.RandomImages
import retrofit2.Response
import retrofit2.http.GET

interface ImagesAPI {
    @GET("users")
    suspend fun getImages(): Response<RandomImages>
}