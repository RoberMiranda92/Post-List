package com.robertomiranda.app.features.postlist.data

import android.content.Context
import androidx.paging.PagedList
import com.robertomiranda.app.features.welcome.data.WelcomeDataProvider
import com.robertomiranda.data.LocalRepository
import com.robertomiranda.data.RemoteRepository
import com.robertomiranda.data.models.Comment
import com.robertomiranda.data.models.Post
import io.reactivex.Flowable
import io.reactivex.functions.BiFunction

class PostListProvider(private val localRepository: LocalRepository) {

    fun getAllPostPaginated(): Flowable<PagedList<Post>> {
        return localRepository.getAllPostPaginated()
    }

    companion object {

        fun newInstance(context: Context): PostListProvider {
            return PostListProvider(LocalRepository.newInstance(context))
        }
    }
}