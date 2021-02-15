package com.example.mvvmtest.api.entity

import com.google.gson.annotations.SerializedName

data class CommentEntity(
    @SerializedName("postId") val postId: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("body") val body: String
)
