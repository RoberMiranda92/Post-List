package com.robertomiranda.data

import com.robertomiranda.data.api.ApiComments
import com.robertomiranda.data.api.ApiPost
import com.robertomiranda.data.api.ApiUsers
import com.robertomiranda.data.api.ServiceFactory
import com.robertomiranda.data.models.Post
import io.reactivex.Maybe
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class RemoteRepository(
    private val apiPost: ApiPost,
    private val apiUsers: ApiUsers,
    private val apiComments: ApiComments
) : IRepository {

    override fun getAllPost(): Maybe<List<Post>> {
        return apiPost.getAllPost()
            .map { postList -> postList.map { it.toModel() } }
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
        fun newInstance(): RemoteRepository {
            return RemoteRepository(
                ServiceFactory.create<ApiPost>(),
                ServiceFactory.create<ApiUsers>(),
                ServiceFactory.create<ApiComments>()
            )
        }
    }
}