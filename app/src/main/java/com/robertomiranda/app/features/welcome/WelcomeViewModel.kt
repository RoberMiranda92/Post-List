package com.robertomiranda.app.features.welcome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.robertomiranda.app.core.*
import com.robertomiranda.app.features.welcome.data.WelcomeDataProvider

class WelcomeViewModel(
    private val provider: WelcomeDataProvider
) : BaseViewModel<WelcomeScreenState>() {

    //Errors
    private val _loadingError: MutableLiveData<Event<Boolean>> = MutableLiveData<Event<Boolean>>()
    val loadingError: LiveData<Event<Boolean>>
        get() = _loadingError

    override fun initState(): WelcomeScreenState = WelcomeScreenState.INITIAL

    fun loadDataFromRemote() {
        provider.getAndCacheAllData()
            .subscribeOnNewObserveOnMain()
            .doOnSubscribe { moveToLoading() }
            .subscribe(
                { moveToDataLoaded() },
                { moveToError(it) }
            ).addToDisposables(disposables)
    }

    private fun moveToLoading() {
        _screenState.setValue(WelcomeScreenState.LOADING_DATA)
    }

    private fun moveToError(error: Throwable) {
        _screenState.setValue(WelcomeScreenState.ERROR)
        _loadingError.setValue(Event(true))

        //TODO: Log error
    }

    private fun moveToDataLoaded() {
        _screenState.setValue(WelcomeScreenState.DATA_LOADED)
    }
}