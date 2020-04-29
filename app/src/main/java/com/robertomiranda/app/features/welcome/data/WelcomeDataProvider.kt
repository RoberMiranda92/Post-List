package com.robertomiranda.app.features.welcome.data

import android.content.Context
import com.robertomiranda.data.LocalRepository
import com.robertomiranda.data.RemoteRepository
import io.reactivex.Completable

class WelcomeDataProvider(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository
) {

    fun getAndCacheAllData(): Completable {
        return remoteRepository.getAllPost()
            .flatMap { localRepository.addAllPost(it).toFlowable() }
            .flatMap { remoteRepository.getAllComments() }
            .flatMap { localRepository.addAllComments(it).toFlowable() }
            .flatMap { remoteRepository.geAllUsers() }
            .flatMap { localRepository.addAllUsers(it).toFlowable() }.ignoreElements()
    }

    companion object {

        fun newInstance(context: Context): WelcomeDataProvider {
            return WelcomeDataProvider(
                RemoteRepository.newInstance(),
                LocalRepository.newInstance(context)
            )
        }
    }
}