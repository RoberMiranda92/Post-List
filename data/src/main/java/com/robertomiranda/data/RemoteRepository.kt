package com.robertomiranda.data

import com.robertomiranda.data.api.ApiComments
import com.robertomiranda.data.api.ApiPost
import com.robertomiranda.data.api.ApiUsers
import com.robertomiranda.data.api.ServiceFactory
import com.robertomiranda.data.models.Comment
import com.robertomiranda.data.models.Post
import io.reactivex.Maybe

class RemoteRepository(
    private val apiPost: ApiPost,
    private val apiUsers: ApiUsers,
    private val apiComments: ApiComments
) : IRepository {

    override fun getAllPost(): Maybe<List<Post>> {
        return apiPost.getAllPost()
            .map { postList -> postList.map { it.toModel() } }
    }

    override fun getPostById(id: Int): Maybe<Post> {
        return apiPost.getPostById(id).map { it.toModel() }
    }

    override fun getAllCommentsFromPost(postId: Int): Maybe<List<Comment>> {
        TODO("Not yet implemented")
    }

    override fun getUserById(id: Int) {
        TODO("Not yet implemented")
    }

    override fun getAllPostFromUser(userID: Int) {
        TODO("Not yet implemented")
    }

    override fun getAllCommentsFromUser(userID: Int) {
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