package com.example.mvvmtest.utils.extensions

import com.example.mvvmtest.api.entity.CommentEntity
import com.example.mvvmtest.api.entity.PostEntity
import com.example.mvvmtest.api.entity.UserEntity
import com.example.mvvmtest.domain.model.Comment
import com.example.mvvmtest.domain.model.Post
import com.example.mvvmtest.domain.model.User

fun PostEntity.toDomain(): Post = Post(userId.toString(), id.toString(), title, body)


fun CommentEntity.toDomain(): Comment = Comment(postId.toString(), id.toString(), name, email, body)

fun UserEntity.toDomain(): User = User(id = id.toString(), name = name)

fun List<PostEntity>.toDomainPostList(): List<Post> {
    var list: MutableList<Post> = mutableListOf()

    this.forEach {
        list.add(it.toDomain())

    }

    return list

}

fun List<CommentEntity>.toDomainCommentList(postId: String): List<Comment> {
    var list: MutableList<Comment> = mutableListOf()

    this.forEach {
        if (postId.equals(it.postId.toString())) {
            list.add(it.toDomain())
        }

    }

    return list

}


fun List<UserEntity>.toDomainUserList(): List<User> {
    var list: MutableList<User> = mutableListOf()

    this.forEach {
        list.add(it.toDomain())
    }

    return list

}