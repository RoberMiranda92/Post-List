package com.robertomiranda.data

import com.robertomiranda.data.api.models.CommentApi
import com.robertomiranda.data.api.models.PostApi
import com.robertomiranda.data.api.models.UserApi

class RemoteRepository(postApi: PostApi, userApi: UserApi, commentApi: CommentApi) : IRepository {

    override fun getAllPost() {
        TODO("Not yet implemented")
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