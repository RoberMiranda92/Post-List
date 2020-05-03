package com.robertomiranda.app.features.postlist.data

import android.content.Context
import androidx.paging.PagedList
import com.robertomiranda.data.repository.local.LocalRepository
import com.robertomiranda.data.models.Post
import io.reactivex.Flowable

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