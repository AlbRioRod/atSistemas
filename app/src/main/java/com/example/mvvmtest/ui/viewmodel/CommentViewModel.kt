package com.example.mvvmtest.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.mvvmtest.base.BaseViewModel
import com.example.mvvmtest.domain.model.Comment
import com.example.mvvmtest.domain.model.Post

class CommentViewModel: BaseViewModel() {
    private val commentUserId = MutableLiveData<String>()
    private val commentPostId = MutableLiveData<String>()
    private val commentName = MutableLiveData<String>()
    private val commentBody = MutableLiveData<String>()

    fun bind(comment: Comment) {
        commentUserId.value = comment.postId
        commentPostId.value = comment.body
        commentName.value = comment.email
        commentBody.value = comment.name

    }

    fun getCommentUserId(): MutableLiveData<String> {
        return commentUserId
    }

    fun getCommentPostId(): MutableLiveData<String> {
        return commentPostId
    }

    fun getCommentBody(): MutableLiveData<String> {
        return commentBody
    }

    fun getCommentName(): MutableLiveData<String> {
        return commentName
    }
}