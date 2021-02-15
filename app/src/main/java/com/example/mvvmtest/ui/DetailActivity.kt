package com.example.mvvmtest.ui

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmtest.R
import com.example.mvvmtest.databinding.ActivityDetailBinding
import com.example.mvvmtest.databinding.ActivityMainBinding
import com.example.mvvmtest.ui.viewmodel.CommentViewModel
import com.example.mvvmtest.ui.viewmodel.DetailViewModel
import com.example.mvvmtest.ui.viewmodel.PostListViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_main.*


class DetailActivity: AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel
    private var errorSnackbar: Snackbar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        binding.postTitle.text = intent.getStringExtra("title")
        binding.postBody.text = intent.getStringExtra("body")
        binding.postUser.text = intent.getStringExtra("user")


        binding.commentList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.commentList.setHasFixedSize(true)

        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })

        viewModel.userName.observe(this, Observer { userName ->
            if (userName != null) showUser(userName) else hideUser()
        })
        binding.viewModel = viewModel

        viewModel.loadComments(intent.getStringExtra("id")!!, intent.getStringExtra("user")!!)
        comment_list.invalidate()
        binding.commentList.adapter?.notifyDataSetChanged()




    }


    private fun showError (@StringRes errorMessage:Int){
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.show()
    }

    private fun hideError(){
        errorSnackbar?.dismiss()
    }

    private fun showUser( userName: String) {
        binding.postUser.text = userName

    }

    private fun hideUser() {
        binding.postUser.visibility = View.GONE
    }



}