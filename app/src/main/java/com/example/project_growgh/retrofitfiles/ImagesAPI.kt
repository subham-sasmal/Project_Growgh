package com.example.project_growgh.retrofitfiles

import retrofit2.Response
import retrofit2.http.GET

interface ImagesAPI {
    @GET("users")
    suspend fun getImages(): Response<RandomImages>
}