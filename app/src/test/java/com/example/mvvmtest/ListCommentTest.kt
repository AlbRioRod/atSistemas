package com.example.mvvmtest

import com.example.mvvmtest.api.entity.CommentEntity
import com.example.mvvmtest.utils.extensions.toDomainCommentList
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

class ListCommentTest {

    private lateinit var listComment: List<CommentEntity>

    @Before
    fun setUp() {
        listComment = listOf(
            CommentEntity(1, 1, "John", "John@mock.com", "test"),
            CommentEntity(5, 2, "Ann", "Ann@mock.com", "test"),
            CommentEntity(3, 3, "Charles", "Charles@mock.com", "test"),
            CommentEntity(7, 4, "Mark", "Mark@mock.com", "test"),
            CommentEntity(2, 5, "Claire", "Claire@mock.com", "test"),
            CommentEntity(1, 6, "Peter", "Peter@mock.com", "test"),
            CommentEntity(2, 7, "Lois", "Lois@mock.com", "test"),
            CommentEntity(5, 8, "Jeff", "Jeff@mock.com", "test")
            )
    }

    @Test
    fun `the comments are filter by postId`() {
        val filteredCommentsById = listComment.toDomainCommentList("1")

        assertThat(filteredCommentsById.size, `is`(2))
        assertThat(filteredCommentsById[0].name, `is`("John"))
        assertThat(filteredCommentsById[1].name,`is`("Peter"))
    }
}