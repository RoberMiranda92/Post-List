package com.robertomiranda.app.features.postlist.data

import androidx.paging.PagedList
import com.robertomiranda.data.models.Post
import com.robertomiranda.data.repository.local.ILocalRepository
import io.reactivex.Flowable

class PostListProvider(private val localRepository: ILocalRepository) {

    fun getAllPostPaginated(): Flowable<PagedList<Post>> {
        return localRepository.getAllPostPaginated()
    }

    companion object {

        fun newInstance(localRepository: ILocalRepository): PostListProvider {
            return PostListProvider(localRepository)
        }
    }
}