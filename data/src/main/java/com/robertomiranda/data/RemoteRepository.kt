package com.robertomiranda.data

import com.robertomiranda.data.api.ApiComments
import com.robertomiranda.data.api.ApiPost
import com.robertomiranda.data.api.ApiUsers
import io.reactivex.Maybe

class RemoteRepository(
    private val postApi: ApiPost,
    private val userApi: ApiUsers,
    private val commentApi: ApiComments
) : IRepository {

    override fun getAllPost(): Maybe<List<Post>> {
        return postApi.getAllPost().map { postList -> postList.map { it.toModel() } }
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
}