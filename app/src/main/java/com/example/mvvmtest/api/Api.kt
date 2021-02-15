package com.example.mvvmtest.api

import com.example.mvvmtest.api.entity.CommentEntity
import com.example.mvvmtest.api.entity.PostEntity
import com.example.mvvmtest.api.entity.UserEntity
import retrofit2.Response
import retrofit2.http.GET

interface Api {


    @GET("posts")
    suspend fun getPosts(): Response<List<PostEntity>>


    @GET("users")
    suspend fun getUsers(): Response<List<UserEntity>>


    @GET("comments")
    suspend fun getComments(): Response<List<CommentEntity>>


}