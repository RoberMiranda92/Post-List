package com.robertomiranda.app.features.postdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.robertomiranda.app.core.BaseViewModel
import com.robertomiranda.app.core.PostDetailScreenState
import com.robertomiranda.app.core.addToDisposables
import com.robertomiranda.app.core.subscribeOnNewObserveOnMain
import com.robertomiranda.data.LocalRepository
import com.robertomiranda.data.models.Comment
import com.robertomiranda.data.models.Post
import io.reactivex.Flowable
import io.reactivex.functions.BiFunction

class PostDetailViewModel(
    private val localRepository: LocalRepository
) : BaseViewModel<PostDetailScreenState>() {

    override fun initState(): PostDetailScreenState = PostDetailScreenState.INITIAL

    //Data
    private val _postData: MutableLiveData<Post> = MutableLiveData()
    val postData: LiveData<Post>
        get() = _postData

    private val _postCommentsData: MutableLiveData<List<Comment>> = MutableLiveData()
    val postCommentsData: LiveData<List<Comment>>
        get() = _postCommentsData

    fun loadPostDetails(postId: Int) {
        val observer: Flowable<Pair<Post, List<Comment>>> = Flowable.zip(
            localRepository.getPostById(postId).toFlowable(),
            localRepository.getAllCommentsFromPost(postId),
            BiFunction { first, second -> Pair(first, second) }
        )

        observer.subscribeOnNewObserveOnMain()
            .doOnSubscribe { moveToLoading() }
            .subscribe(
                { values -> managePostDetail(values.first, values.second) },
                { error -> moveToError() }
            ).addToDisposables(disposables)
    }

    private fun managePostDetail(post: Post, comments: List<Comment>) {
        _postData.setValue(post)
        _postCommentsData.setValue(comments)

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