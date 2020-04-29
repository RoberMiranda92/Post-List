package com.robertomiranda.app.features.postdetail.data

import android.content.Context
import com.robertomiranda.data.LocalRepository
import com.robertomiranda.data.models.Comment
import com.robertomiranda.data.models.Post
import io.reactivex.Flowable
import io.reactivex.functions.BiFunction

class PostDetailProvider(private val localRepository: LocalRepository) {

    fun getPostDetail(postId: Int): Flowable<Pair<Post, List<Comment>>> {
        return Flowable.zip(
            localRepository.getPostById(postId).toFlowable(),
            localRepository.getAllCommentsFromPost(postId)
                .onErrorReturn { ERROR_LIST },
            BiFunction { first, second -> Pair(first, second) }
        )
    }

    companion object {
        private val INVALID_COMMENT_ERROR: Comment = Comment("", -1, -1, "", "")
        val ERROR_LIST = listOf<Comment>(INVALID_COMMENT_ERROR)

        fun newInstance(context: Context): PostDetailProvider {
            return PostDetailProvider(LocalRepository.newInstance(context))
        }
    }
}