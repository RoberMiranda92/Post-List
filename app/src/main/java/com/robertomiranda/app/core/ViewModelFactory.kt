package com.robertomiranda.app.core

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.robertomiranda.app.features.postdetail.PostDetailViewModel
import com.robertomiranda.app.features.postdetail.data.PostDetailProvider
import com.robertomiranda.app.features.postlist.PostListViewModel
import com.robertomiranda.app.features.postlist.data.PostListProvider
import com.robertomiranda.app.features.welcome.WelcomeViewModel
import com.robertomiranda.app.features.welcome.data.WelcomeDataProvider

class ViewModelFactory private constructor(private val context: Context) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        with(modelClass) {
            when {
                isAssignableFrom(PostListViewModel::class.java) -> {
                    PostListViewModel(PostListProvider.newInstance(context))
                }
                isAssignableFrom(PostDetailViewModel::class.java) -> {
                    PostDetailViewModel(PostDetailProvider.newInstance(context))
                }

                isAssignableFrom(WelcomeViewModel::class.java) -> {
                    WelcomeViewModel(WelcomeDataProvider.newInstance(context))
                }
                else -> error("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T


    companion object {

        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(ViewModelFactory::class.java) {
                INSTANCE ?: ViewModelFactory(context)
                    .also { INSTANCE = it }
            }

        @VisibleForTesting
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}