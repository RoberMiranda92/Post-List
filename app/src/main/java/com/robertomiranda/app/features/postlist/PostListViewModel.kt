package com.robertomiranda.app.features.postlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.robertomiranda.app.core.BaseViewModel
import com.robertomiranda.app.core.PostListScreenState
import com.robertomiranda.app.core.addToDisposables
import com.robertomiranda.app.core.subscribeOnNewObserveOnMain
import com.robertomiranda.app.features.postlist.data.PostListProvider
import com.robertomiranda.data.models.Post

class PostListViewModel(
    private val provider: PostListProvider
) :
    BaseViewModel<PostListScreenState>() {

    override fun initState(): PostListScreenState = PostListScreenState.INITIAL

    private val _postList: MutableLiveData<PagedList<Post>> = MutableLiveData()
    val postList: LiveData<PagedList<Post>>
        get() = _postList

    fun loadPostList() {
        provider.getAllPostPaginated()
            .subscribeOnNewObserveOnMain()
            .doOnSubscribe { moveToLoading() }
            .subscribe(
                { list -> manageList(list) },
                { error -> moveToError() }
            ).addToDisposables(disposables)
    }

    private fun manageList(list: PagedList<Post>) {
        if (list.isEmpty()) {
            moveToEmptyData()
        } else {
            moveToDataLoaded()
            _postList.setValue(list)
        }
    }

    private fun moveToLoading(){
        _screenState.setValue(PostListScreenState.LOADING_DATA)
    }

    private fun moveToError() {
        _screenState.setValue(PostListScreenState.ERROR)
    }

    private fun moveToDataLoaded() {
        _screenState.setValue(PostListScreenState.DATA_LOADED)
    }

    private fun moveToEmptyData() {
        _screenState.setValue(PostListScreenState.EMPTY_DATA)
    }
}