package com.robertomiranda.app.features.postdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.robertomiranda.app.core.*
import com.robertomiranda.app.core.list.ListItem
import com.robertomiranda.app.features.postdetail.adapter.models.CommentBundle
import com.robertomiranda.app.features.postdetail.data.model.PostDetail
import com.robertomiranda.app.features.postdetail.data.PostDetailProvider
import com.robertomiranda.data.models.Comment
import com.robertomiranda.data.models.Resource
import io.reactivex.Maybe

class PostDetailViewModel(
    private val provider: PostDetailProvider
) : BaseViewModel<PostDetailScreenState>() {

    override fun initState(): PostDetailScreenState = PostDetailScreenState.INITIAL

    //Data
    private val _postData: MutableLiveData<PostDetail> = MutableLiveData()
    val postData: LiveData<PostDetail>
        get() = _postData

    private val _postCommentsData: MutableLiveData<List<Comment>> = MutableLiveData()
    val postCommentsData: LiveData<MutableList<ListItem>>
        get() = _postCommentsData.map { CommentBundle(it) }.map { it.all }

    //Error
    private val _commentError: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val commentError: LiveData<Event<Boolean>>
        get() = _commentError

    fun loadPostDetails(postId: Int) {
        provider.getPostDetail(postId)
            .subscribeOnNewObserveOnMain()
            .doOnSubscribe { moveToLoading() }
            .subscribe(
                { detail -> managePostDetail(detail) },
                { error -> moveToError() }
            ).addToDisposables(disposables)
    }

    fun loadCommentResource(email: String): Maybe<Resource> {
       return  provider.getResourceFromEmail(email)
            .subscribeOnNewObserveOnMain()
    }

    private fun managePostDetail(detail: PostDetail) {
        _postData.setValue(detail)
        if (PostDetailProvider.ERROR_LIST == detail.comments) {
            _commentError.setValue(Event(true))
        } else {
            _postCommentsData.setValue(detail.comments)
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