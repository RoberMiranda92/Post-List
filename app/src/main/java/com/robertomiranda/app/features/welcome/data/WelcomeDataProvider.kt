package com.robertomiranda.app.features.welcome.data

import android.content.Context
import com.robertomiranda.data.repository.local.ILocalRepository
import com.robertomiranda.data.repository.local.LocalRepository
import com.robertomiranda.data.repository.remote.IRemoteRepository
import com.robertomiranda.data.repository.remote.RemoteRepository
import io.reactivex.Completable

class WelcomeDataProvider(
    private val remoteRepository: IRemoteRepository,
    private val localRepository: ILocalRepository
) {

    fun getAndCacheAllData(): Completable {
        return remoteRepository.getAllPost()
            .flatMap { localRepository.addAllPost(it).toFlowable() }
            .flatMap { remoteRepository.getAllComments() }
            .flatMap { localRepository.addAllComments(it).toFlowable() }
            .flatMap { remoteRepository.geAllUsers() }
            .flatMap { localRepository.addAllUsers(it).toFlowable() }
            .flatMap { remoteRepository.getAllResources() }
            .flatMap { localRepository.addAllResources(it).toFlowable() }
            .ignoreElements()
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