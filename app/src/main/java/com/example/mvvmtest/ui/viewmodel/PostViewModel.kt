package com.example.mvvmtest.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.mvvmtest.base.BaseViewModel
import com.example.mvvmtest.domain.model.Post

class PostViewModel: BaseViewModel() {
    private val postUserId = MutableLiveData<String>()
    private val postId = MutableLiveData<String>()
    private val postTitle = MutableLiveData<String>()
    private val postBody = MutableLiveData<String>()

    fun bind(post: Post) {
        postUserId.value = post.userId
        postId.value = post.id
        postTitle.value = post.title
        postBody.value = post.body

    }

    fun getPostUserId(): MutableLiveData<String> {
        return postUserId
    }

    fun getPostId(): MutableLiveData<String> {
        return postId
    }

    fun getPostTitle(): MutableLiveData<String> {
        return postTitle
    }

    fun getPostBody(): MutableLiveData<String> {
        return postBody
    }
}