package com.example.mvvmtest.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.mvvmtest.api.Api
import com.example.mvvmtest.base.BaseViewModel
import com.example.mvvmtest.domain.model.Post
import com.example.mvvmtest.ui.adapters.PostListAdapter
import com.example.mvvmtest.utils.extensions.toDomain
import com.example.mvvmtest.utils.extensions.toDomainPostList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class PostListViewModel : BaseViewModel() {
    @Inject
    lateinit var api: Api
    val postListAdapter: PostListAdapter = PostListAdapter()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()



    fun loadPosts() {

        CoroutineScope(Dispatchers.Main).launch() {
            val response = api.getPosts()
            try {
                withContext(Dispatchers.IO) {
                    if (response.isSuccessful) {

                        response.body()?.let { onRetrievePostListSuccess(it.toDomainPostList()) }

                    }
                }
            } finally {
                postListAdapter.notifyDataSetChanged()
            }


        }
    }


    private fun onRetrievePostListSuccess(postsList:List<Post>){
        postListAdapter.updatePostList(postsList)
    }

}