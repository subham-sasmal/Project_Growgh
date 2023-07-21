package com.example.project_growgh.ApiFiles.videosApi.jsontokotlinfiles

data class Post(
    val comment: Comment,
    val creator: Creator,
    val postId: String,
    val reaction: Reaction,
    val submission: Submission
)