package com.robertomiranda.app.features.postdetail.data

import android.content.Context
import com.robertomiranda.app.features.postdetail.data.model.PostDetail
import com.robertomiranda.data.models.Comment
import com.robertomiranda.data.models.Resource
import com.robertomiranda.data.repository.local.ILocalRepository
import com.robertomiranda.data.repository.local.LocalRepository
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.functions.BiFunction

class PostDetailProvider(private val localRepository: ILocalRepository) {

    fun getPostDetail(postId: Int): Flowable<PostDetail> {
        val builder: PostDetail.PostDetailBuilder = PostDetail.PostDetailBuilder()
        return Flowable.zip(
            getPostWithUser(postId, builder),
            localRepository.getAllCommentsFromPost(postId)
                .onErrorReturn { ERROR_LIST },
            BiFunction { builder, comments -> builder.setComments(comments).build() }
        )
    }

    fun getResourceFromEmail(email: String): Maybe<Resource> {
        return localRepository.getResourceByKey(email.substring(email.indexOfFirst { it == '.' }))
    }

    private fun getPostWithUser(
        postId: Int,
        builder: PostDetail.PostDetailBuilder
    ): Flowable<PostDetail.PostDetailBuilder> {
        return localRepository.getPostById(postId).toFlowable()
            .map {
                builder.setPost(it)
                it
            }
            .flatMap {
                localRepository.getUserById(it.userId).toFlowable()
            }
            .map { builder.setUser(it) }
    }

    companion object {
        private val INVALID_COMMENT_ERROR: Comment = Comment("", -1, -1, "", "")
        val ERROR_LIST = listOf<Comment>(INVALID_COMMENT_ERROR)

        fun newInstance(context: Context): PostDetailProvider {
            return PostDetailProvider(LocalRepository.newInstance(context))
        }
    }
}