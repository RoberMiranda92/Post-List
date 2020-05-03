package com.robertomiranda.app.features.postlist.data

import android.content.Context
import androidx.paging.PagedList
import com.robertomiranda.data.models.Post
import com.robertomiranda.data.repository.local.ILocalRepository
import com.robertomiranda.data.repository.local.LocalRepository
import io.reactivex.Flowable

class PostListProvider(private val localRepository: ILocalRepository) {

    fun getAllPostPaginated(): Flowable<PagedList<Post>> {
        return localRepository.getAllPostPaginated()
    }

    companion object {

        fun newInstance(context: Context, localRepository: ILocalRepository = LocalRepository.newInstance(context)): PostListProvider {
            return PostListProvider(localRepository)
        }
    }
}