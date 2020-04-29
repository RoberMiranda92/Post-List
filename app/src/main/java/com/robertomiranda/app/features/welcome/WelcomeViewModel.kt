package com.robertomiranda.app.features.welcome

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.robertomiranda.app.core.BaseViewModel
import com.robertomiranda.app.core.WelcomeScreenState
import com.robertomiranda.app.core.addToDisposables
import com.robertomiranda.app.core.subscribeOnNewObserveOnMain
import com.robertomiranda.data.LocalRepository
import com.robertomiranda.data.RemoteRepository
import com.robertomiranda.data.models.Post
import io.reactivex.Completable

class WelcomeViewModel(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository
) : BaseViewModel<WelcomeScreenState>() {

    override fun initState(): WelcomeScreenState = WelcomeScreenState.INITIAL
    
    fun loadDataFromRemote() {
        //TODO CREATE USE CASE OR PROVIDER
        remoteRepository.getAllPost()
            .flatMap { localRepository.addAllPost(it).toFlowable() }
            .flatMap { remoteRepository.getAllComments() }
            .flatMap { localRepository.addAllComments(it).toFlowable() }
            .flatMap { remoteRepository.geAllUsers() }
            .flatMap { localRepository.addAllUsers(it).toFlowable() }
            .subscribeOnNewObserveOnMain()
            .doOnSubscribe { moveToLoading() }
            .subscribe(
                { Log.d("WelcomeViewModel", "data inserted") },
                { Log.e("WelcomeViewModel", it.message) }
            ).addToDisposables(disposables)
    }

    private fun moveToLoading() {
        _screenState.setValue(WelcomeScreenState.LOADING_DATA)
    }

    private fun moveToError() {
        _screenState.setValue(WelcomeScreenState.ERROR)
    }

    private fun moveToDataLoaded() {
        _screenState.setValue(WelcomeScreenState.DATA_LOADED)
    }

}