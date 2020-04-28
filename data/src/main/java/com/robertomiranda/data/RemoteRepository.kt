package com.robertomiranda.data

import com.robertomiranda.data.api.ApiComments
import com.robertomiranda.data.api.ApiPost
import com.robertomiranda.data.api.ApiUsers
import com.robertomiranda.data.api.ServiceFactory
import com.robertomiranda.data.models.Comment
import com.robertomiranda.data.models.Post
import com.robertomiranda.data.models.User
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
        return getAllComments().filter { list -> list.all { it.id == postId } }
    }

    fun getAllComments(): Maybe<List<Comment>> {
        return apiComments.getAllComments().map { commentList -> commentList.map { it.toModel() } }
    }

    fun geAllUsers(): Maybe<List<User>> {
        return apiUsers.getAllUsers().map { list -> list.map { it.toModel() } }
    }

    override fun getUserById(id: Int) {
        TODO("Not yet implemented")
    }

    override fun getAllPostFromUser(userId: Int): Maybe<List<Post>> {
        return getAllPost().filter { list -> list.all { it.userId == userId } }
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