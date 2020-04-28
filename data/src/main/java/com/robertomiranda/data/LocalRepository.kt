package com.robertomiranda.data

import android.content.Context
import com.robertomiranda.data.models.Comment
import com.robertomiranda.data.models.Post
import com.robertomiranda.data.room.DaoFactory
import com.robertomiranda.data.room.dao.CommentsDao
import com.robertomiranda.data.room.dao.PostsDao
import com.robertomiranda.data.room.dao.UsersDao
import io.reactivex.Maybe

class LocalRepository(
    private val postsDao: PostsDao,
    private val usersDao: UsersDao,
    private val commentsDao: CommentsDao
) : IRepository {

    override fun getAllPost(): Maybe<List<Post>> {
        return postsDao.getAllPost().map { postList -> postList.map { it.toModel() } }
    }

    override fun getPostById(id: Int): Maybe<Post> {
        return postsDao.getPostById(id).map { it.toModel() }
    }

    override fun getAllCommentsFromPost(postId: Int):Maybe<List<Comment>> {
        return commentsDao.getCommentByPostId(postId).map { commentList -> commentList.map { it.toModel() } }
    }

    override fun getUserById(id: Int) {
        TODO("Not yet implemented")
    }

    override fun getAllPostFromUser(userId: Int): Maybe<List<Post>> {
        TODO("Not yet implemented")
    }

    override fun getAllCommentsFromUser(userID: Int) {
        TODO("Not yet implemented")
    }

    companion object {

        fun newInstance(context: Context): LocalRepository {
            return LocalRepository(
                DaoFactory.postDao(context),
                DaoFactory.usersDao(context),
                DaoFactory.commentsDao(context)
            )
        }
    }
}