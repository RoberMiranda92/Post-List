package com.robertomiranda.data

import android.content.Context
import androidx.paging.PagedList
import androidx.paging.toFlowable
import com.robertomiranda.data.models.Comment
import com.robertomiranda.data.models.Post
import com.robertomiranda.data.room.DaoFactory
import com.robertomiranda.data.room.dao.CommentsDao
import com.robertomiranda.data.room.dao.PostsDao
import com.robertomiranda.data.room.dao.UsersDao
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe

class LocalRepository constructor(
    private val postsDao: PostsDao,
    private val usersDao: UsersDao,
    private val commentsDao: CommentsDao
) : IRepository {

    override fun getAllPost(): Flowable<List<Post>> {
        return postsDao.getAllPost()
            .map { postList -> postList.map { it.toModel() } }
    }

    override fun getAllPostPaginated(): Flowable<PagedList<Post>> {
        return postsDao.getAllPostPaginated()
            .mapByPage { pageList -> pageList.map { it.toModel() } }
            .toFlowable(PAGINATION_SIZE)
    }

    fun addAllPost(postList: List<Post>): Completable {
        return postsDao.addPostList(postList.map { it.toEntity() })
    }

    override fun getPostById(id: Int): Maybe<Post> {
        return postsDao.getPostById(id).map { it.toModel() }
    }

    override fun getAllCommentsFromPost(postId: Int): Flowable<List<Comment>> {
        return commentsDao.getCommentByPostId(postId)
            .map { commentList -> commentList.map { it.toModel() } }
    }

    companion object {
        const val PAGINATION_SIZE = 5

        fun newInstance(context: Context): LocalRepository {
            return LocalRepository(
                DaoFactory.postDao(context),
                DaoFactory.usersDao(context),
                DaoFactory.commentsDao(context)
            )
        }
    }
}