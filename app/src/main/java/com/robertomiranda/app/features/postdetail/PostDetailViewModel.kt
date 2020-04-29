package com.robertomiranda.app.features.postdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.robertomiranda.app.core.*
import com.robertomiranda.app.features.postdetail.data.PostDetailProvider
import com.robertomiranda.data.models.Comment
import com.robertomiranda.data.models.Post

class PostDetailViewModel(
    private val provider: PostDetailProvider
) : BaseViewModel<PostDetailScreenState>() {

    override fun initState(): PostDetailScreenState = PostDetailScreenState.INITIAL

    //Data
    private val _postData: MutableLiveData<Post> = MutableLiveData()
    val postData: LiveData<Post>
        get() = _postData

    private val _postCommentsData: MutableLiveData<List<Comment>> = MutableLiveData()
    val postCommentsData: LiveData<List<Comment>>
        get() = _postCommentsData

    //Error
    private val _commentError: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val commentError: LiveData<Event<Boolean>>
        get() = _commentError

    fun loadPostDetails(postId: Int) {

        provider.getPostDetail(postId)
            .subscribeOnNewObserveOnMain()
            .doOnSubscribe { moveToLoading() }
            .subscribe(
                { values -> managePostDetail(values.first, values.second) },
                { error -> moveToError() }
            ).addToDisposables(disposables)
    }

    private fun managePostDetail(post: Post, comments: List<Comment>) {
        _postData.setValue(post)
        if (PostDetailProvider.ERROR_LIST == comments) {
            _commentError.setValue(Event(true))
        } else {
            _postCommentsData.setValue(comments)
        }

        moveToDataLoaded()
    }

    private fun moveToLoading() {
        _screenState.setValue(PostDetailScreenState.LOADING_DATA)
    }

    private fun moveToError() {
        _screenState.setValue(PostDetailScreenState.ERROR)
    }

    private fun moveToDataLoaded() {
        _screenState.setValue(PostDetailScreenState.DATA_LOADED)
    }
}