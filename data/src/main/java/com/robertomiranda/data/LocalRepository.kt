package com.robertomiranda.data

import android.content.Context
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

    override fun getPostById(id: String) {
        TODO("Not yet implemented")
    }

    override fun getAllCommentsFromPost(postId: String) {
        TODO("Not yet implemented")
    }

    override fun getUserById(id: String) {
        TODO("Not yet implemented")
    }

    override fun getAllPostFromUser(userID: String) {
        TODO("Not yet implemented")
    }

    override fun getAllCommentsFromUser(userID: String) {
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