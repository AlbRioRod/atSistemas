package com.example.mvvmtest.base

import androidx.lifecycle.ViewModel
import com.example.mvvmtest.injection.component.DaggerViewModelInjector
import com.example.mvvmtest.injection.component.ViewModelInjector
import com.example.mvvmtest.injection.module.NetworkModule
import com.example.mvvmtest.ui.viewmodel.CommentViewModel
import com.example.mvvmtest.ui.viewmodel.DetailViewModel
import com.example.mvvmtest.ui.viewmodel.PostListViewModel
import com.example.mvvmtest.ui.viewmodel.PostViewModel

abstract class BaseViewModel : ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()


    init {
        inject()
    }


    private fun inject() {
        when (this) {

            is PostViewModel -> injector.inject(this)
            is PostListViewModel -> injector.inject(this)
            is DetailViewModel -> injector.inject(this)
            is CommentViewModel -> injector.inject(this)

        }
    }
}