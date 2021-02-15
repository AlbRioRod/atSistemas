package com.example.mvvmtest.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmtest.R
import com.example.mvvmtest.databinding.ItemListBinding
import com.example.mvvmtest.domain.model.Post
import com.example.mvvmtest.ui.DetailActivity
import com.example.mvvmtest.ui.MainActivity
import com.example.mvvmtest.ui.viewmodel.PostViewModel
import kotlin.coroutines.coroutineContext

class PostListAdapter() : RecyclerView.Adapter<PostListAdapter.ViewHolder>() {
    private  val postList = ArrayList<Post>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListAdapter.ViewHolder {
        val binding: ItemListBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_list, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostListAdapter.ViewHolder, position: Int) {
        holder.bind(postList[position])
        holder.itemView.setOnClickListener {
            val post =postList.get(position)

            val intent = Intent(it.context,DetailActivity::class.java)
            intent.putExtra("id",post.id)
            intent.putExtra("title", post.title)
            intent.putExtra("user", post.userId)
            intent.putExtra("body",post.body)
            startActivity(it.context,intent,null)
        }

    }


    override fun getItemCount(): Int = postList.size


    fun updatePostList(postList: List<Post>) {

        this.postList.clear()
        this.postList.addAll(postList)

    }

    class ViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {

        private val viewModel = PostViewModel()
        fun bind(post: Post) {
            viewModel.bind(post)
            binding.postViewModel = viewModel
            itemView.setOnClickListener {


            }
        }


    }
}