package com.robertomiranda.app.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel<S : ScreenState> : ViewModel() {

    // State
    protected val _screenState: MutableLiveData<S> by lazy { MutableLiveData<S>(initState()) }
    val screenState: LiveData<S>
        get() = _screenState

    //Observables
    protected val disposables: CompositeDisposable = CompositeDisposable()

    abstract fun initState(): S

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }

}