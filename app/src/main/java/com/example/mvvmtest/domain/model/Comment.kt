package com.example.mvvmtest.domain.model

data class Comment(
    var postId: String,
    var id: String,
    var name: String,
    var email: String,
    var body: String
)