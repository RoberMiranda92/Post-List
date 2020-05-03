package com.robertomiranda.data.repository.local

import android.content.Context
import androidx.paging.PagedList
import androidx.paging.toFlowable
import com.robertomiranda.data.models.Comment
import com.robertomiranda.data.models.Post
import com.robertomiranda.data.models.Resource
import com.robertomiranda.data.models.User
import com.robertomiranda.data.room.DaoFactory
import com.robertomiranda.data.room.dao.CommentsDao
import com.robertomiranda.data.room.dao.PostsDao
import com.robertomiranda.data.room.dao.ResourcesDao
import com.robertomiranda.data.room.dao.UsersDao
import com.robertomiranda.data.toEntity
import com.robertomiranda.data.toModel
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

class LocalRepository constructor(
    private val postsDao: PostsDao,
    private val usersDao: UsersDao,
    private val commentsDao: CommentsDao,
    private val resourceDao: ResourcesDao
) : ILocalRepository {

    override fun getAllPost(): Flowable<List<Post>> {
        return postsDao.getAllPost()
            .map { postList -> postList.map { it.toModel() } }
    }

    override fun getAllPostPaginated(): Flowable<PagedList<Post>> {
        return postsDao.getAllPostPaginated()
            .mapByPage { pageList -> pageList.map { it.toModel() } }
            .toFlowable(PAGINATION_SIZE)
    }

    override fun getPostById(id: Int): Single<Post> {
        return postsDao.getPostById(id).map { it.toModel() }
    }

    override fun getAllCommentsFromPost(postId: Int): Flowable<List<Comment>> {
        return commentsDao.getCommentByPostId(postId)
            .map { commentList -> commentList.map { it.toModel() } }
    }

    override fun getUserById(id: Int): Single<User> {
        return usersDao.getUserById(id).map { it.toModel() }
    }

    override fun getAllResources(): Flowable<List<Resource>> {
        return resourceDao.getAllResources()
            .map { postList -> postList.map { it.toModel() } }
    }

    override fun addAllPost(postList: List<Post>): Maybe<List<Long>> {
        return postsDao.addPostList(postList.map { it.toEntity() })
    }

    override fun addAllComments(commentList: List<Comment>): Maybe<List<Long>> {
        return commentsDao.addCommentsList(commentList.map { it.toEntity() })
    }

    override fun addAllUsers(userList: List<User>): Maybe<List<Long>> {
        return usersDao.addAll(userList.map { it.toEntity() })
    }

    override fun addAllResources(resourceList: List<Resource>): Maybe<List<Long>> {
        return resourceDao.addAll(resourceList.map { it.toEntity() })
    }

    companion object {
        const val PAGINATION_SIZE = 5

        fun newInstance(context: Context): LocalRepository {
            return LocalRepository(
                DaoFactory.postDao(context),
                DaoFactory.usersDao(context),
                DaoFactory.commentsDao(context),
                DaoFactory.resourcesDao(context)
            )
        }
    }
}