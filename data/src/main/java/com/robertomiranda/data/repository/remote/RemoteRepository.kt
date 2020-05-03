package com.robertomiranda.data.repository.remote

import com.robertomiranda.data.api.*
import com.robertomiranda.data.api.models.ResourceApi
import com.robertomiranda.data.models.Comment
import com.robertomiranda.data.models.Post
import com.robertomiranda.data.models.Resource
import com.robertomiranda.data.models.User
import com.robertomiranda.data.toModel
import io.reactivex.Flowable
import io.reactivex.Single

class RemoteRepository(
    private val apiPost: ApiPost,
    private val apiUsers: ApiUsers,
    private val apiComments: ApiComments,
    private val apiResource: ApiResources
) : IRemoteRepository {

    override fun getAllPost(): Flowable<List<Post>> {
        return apiPost.getAllPost()
            .map { postList -> postList.map { it.toModel() } }
    }

    override fun getPostById(id: Int): Single<Post> {
        return apiPost.getPostById(id).map { it.toModel() }
    }

    override fun getAllComments(): Flowable<List<Comment>> {
        return apiComments.getAllComments().map { commentList -> commentList.map { it.toModel() } }
    }

    override fun getAllCommentsFromPost(postId: Int): Flowable<List<Comment>> {
        return getAllComments().map { list -> list.filter { it.postId == postId } }
    }

    override fun getUserById(id: Int): Single<User> {
        return apiUsers.getUserById(id).map { it.toModel() }
    }

    override fun geAllUsers(): Flowable<List<User>> {
        return apiUsers.getAllUsers().map { list -> list.map { it.toModel() } }
    }

    override fun getAllResources(): Flowable<List<Resource>> {
        return apiResource.getResources()
            .onErrorReturn { RESOURCE_LIST_DEFAULT }
            .map { list -> list.map { it.toModel() } }
    }

    companion object {
        fun newInstance(): RemoteRepository {
            return RemoteRepository(
                ServiceFactory.create<ApiPost>(),
                ServiceFactory.create<ApiUsers>(),
                ServiceFactory.create<ApiComments>(),
                ServiceFactory.create<ApiResources>()
            )
        }

        val RESOURCE_LIST_DEFAULT = listOf<ResourceApi>(
            ResourceApi(".info", "ℹ️"),
            ResourceApi(".co.uk", "\uD83C\uDDEC\uD83C\uDDE7")
        )
    }
}