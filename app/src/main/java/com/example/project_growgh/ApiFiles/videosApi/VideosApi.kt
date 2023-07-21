package com.example.project_growgh.ApiFiles.videosApi

import com.example.project_growgh.ApiFiles.imagesApi.jsontokotlinfiles.RandomImages
import com.example.project_growgh.ApiFiles.videosApi.jsontokotlinfiles.ShortVideosApi
import com.example.project_growgh.ApiFiles.videosApi.jsontokotlinfiles.Submission
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface VideosApi {
    @GET("videos")
    suspend fun getVideos(@Query("page") pageNumber: Int): Response<ShortVideosApi>
}