package com.example.mvvmtest.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmtest.R
import com.example.mvvmtest.databinding.ItemCommentListBinding
import com.example.mvvmtest.databinding.ItemListBinding
import com.example.mvvmtest.domain.model.Comment
import com.example.mvvmtest.ui.DetailActivity
import com.example.mvvmtest.ui.viewmodel.CommentViewModel

class CommentListAdapter() : RecyclerView.Adapter<CommentListAdapter.ViewHolder>() {
    private val commentList = ArrayList<Comment>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommentListAdapter.ViewHolder {
        val binding: ItemCommentListBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_comment_list,
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentListAdapter.ViewHolder, position: Int) {
        holder.bind(commentList[position])


    }


    override fun getItemCount(): Int = commentList.size


    fun updateCommentList(commentList: List<Comment>) {

        this.commentList.clear()
        this.commentList.addAll(commentList)

    }

    class ViewHolder(private val binding: ItemCommentListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val viewModel = CommentViewModel()
        fun bind(comment: Comment) {
            viewModel.bind(comment)
            binding.commentViewModel = viewModel
            itemView.setOnClickListener {


            }
        }


    }
}