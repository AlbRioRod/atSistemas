package com.example.mvvmtest.injection.component

import com.example.mvvmtest.injection.module.NetworkModule
import com.example.mvvmtest.ui.viewmodel.CommentViewModel
import com.example.mvvmtest.ui.viewmodel.DetailViewModel
import com.example.mvvmtest.ui.viewmodel.PostListViewModel
import com.example.mvvmtest.ui.viewmodel.PostViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector{
    fun inject(postViewModel: PostViewModel)
    fun inject(postListViewModel: PostListViewModel)
    fun inject(detailViewModel: DetailViewModel)
    fun inject(commentViewModel: CommentViewModel)


    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}