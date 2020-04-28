package com.robertomiranda.data

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.paging.toFlowable
import com.robertomiranda.data.models.Comment
import com.robertomiranda.data.models.Post
import com.robertomiranda.data.models.User
import com.robertomiranda.data.room.DaoFactory
import com.robertomiranda.data.room.dao.CommentsDao
import com.robertomiranda.data.room.dao.PostsDao
import com.robertomiranda.data.room.dao.UsersDao
import io.reactivex.Flowable
import io.reactivex.Maybe

class LocalRepository @VisibleForTesting constructor(
    private val postsDao: PostsDao,
    private val usersDao: UsersDao,
    private val commentsDao: CommentsDao
) : IRepository {

    override fun getAllPost(): Flowable<List<Post>> {
        return postsDao.getAllPost().toFlowable(PAGINATION_SIZE)
            .map { postList -> postList.filterNotNull().map { it.toModel() } }
    }

    override fun getPostById(id: Int): Maybe<Post> {
        return postsDao.getPostById(id).map { it.toModel() }
    }

    override fun getAllCommentsFromPost(postId: Int): Flowable<List<Comment>> {
        return commentsDao.getCommentByPostId(postId)
            .map { commentList -> commentList.map { it.toModel() } }
    }

    companion object {
        const val PAGINATION_SIZE = 10

        fun newInstance(context: Context): LocalRepository {
            return LocalRepository(
                DaoFactory.postDao(context),
                DaoFactory.usersDao(context),
                DaoFactory.commentsDao(context)
            )
        }
    }
}