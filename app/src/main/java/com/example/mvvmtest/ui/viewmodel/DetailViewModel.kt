package com.example.mvvmtest.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.mvvmtest.api.Api
import com.example.mvvmtest.base.BaseViewModel
import com.example.mvvmtest.domain.model.Comment
import com.example.mvvmtest.domain.model.User
import com.example.mvvmtest.ui.adapters.CommentListAdapter
import com.example.mvvmtest.utils.extensions.toDomainCommentList
import com.example.mvvmtest.utils.extensions.toDomainUserList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetailViewModel : BaseViewModel() {
    @Inject
    lateinit var api: Api
    val commentListAdapter: CommentListAdapter = CommentListAdapter()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    var userName: MutableLiveData<String> = MutableLiveData()

    fun loadComments(postId: String, userId: String) {

        CoroutineScope(Dispatchers.Main).launch() {
            val response = api.getComments()
            try {
                withContext(Dispatchers.IO) {
                    if (response.isSuccessful) {

                        response.body()
                            ?.let { onRetrieveCommentListSuccess(it.toDomainCommentList(postId), userId) }

                    } else {
                        onRetrieveCommentListError()
                    }
                }
            } finally {
                commentListAdapter.notifyDataSetChanged()
            }


        }
    }


    private fun onRetrieveCommentListSuccess(commentList: List<Comment>, userId: String) {

        CoroutineScope(Dispatchers.Main).launch() {
            val response = api.getUsers()
            try {
                withContext(Dispatchers.IO) {
                    if (response.isSuccessful) {

                        response.body()
                            ?.let { onRetrieveUserListSuccess(it.toDomainUserList(), commentList, userId) }

                    } else {
                        onRetrieveCommentListError()
                    }
                }
            } finally {
                commentListAdapter.notifyDataSetChanged()
            }


        }
        commentListAdapter.updateCommentList(commentList)
    }


    private fun onRetrieveUserListSuccess(userList: List<User>, commentList: List<Comment>, userId: String) {

        var usersIdInPost = commentList.map { it -> it.id }

        var userFiltered = userList.filter {
            it.id in usersIdInPost
        }

        userList.forEach {
            for (user in userList) {
                if (it.id.equals(userId)){
                    userName.postValue(it.name)
                }
            }
        }

        commentList.forEach {
            for (user in userFiltered ) {
                if (it.id.equals(user.id)){
                    it.email = user.name
                }
            }
        }



        commentListAdapter.updateCommentList(commentList)


    }

    private fun onRetrieveCommentListError() {


    }

    override fun onCleared() {
        super.onCleared()
    }


}