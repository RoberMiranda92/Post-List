package com.robertomiranda.app.features.welcome

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.robertomiranda.app.RxBaseTest
import com.robertomiranda.app.core.Event
import com.robertomiranda.app.core.ScreenState
import com.robertomiranda.app.core.WelcomeScreenState
import com.robertomiranda.app.features.welcome.data.WelcomeDataProvider
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Completable
import org.junit.Rule
import org.junit.Test

class WelcomeViewModelTest : RxBaseTest() {

    private lateinit var viewModel: WelcomeViewModel
    private val provider: WelcomeDataProvider = mockk<WelcomeDataProvider>()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    override fun setUpChild() {
        super.setUpChild()
        viewModel = WelcomeViewModel(provider)
    }

    @Test
    fun loadAllDataOK() {

        val errorObserver: Observer<Event<Boolean>> = mockk(relaxed = true)
        val screenObserver: Observer<ScreenState> = mockk(relaxed = true)

        every { provider.getAndCacheAllData() } returns Completable.complete()

        viewModel.screenState.observeForever(screenObserver)
        viewModel.loadingError.observeForever(errorObserver)

        viewModel.loadDataFromRemote()

        verify(exactly = 1) { screenObserver.onChanged(WelcomeScreenState.INITIAL) }
        verify(exactly = 1) { screenObserver.onChanged(WelcomeScreenState.LOADING_DATA) }
        verify(exactly = 1) { provider.getAndCacheAllData() }
        verify(exactly = 1) { screenObserver.onChanged(WelcomeScreenState.DATA_LOADED) }

        confirmVerified(screenObserver)
        confirmVerified(errorObserver)
        confirmVerified(provider)

        viewModel.screenState.removeObserver(screenObserver)
        viewModel.loadingError.removeObserver(errorObserver)
    }

    @Test
    fun loadAllDataKO() {
        val loadError = Error("My error")

        val errorObserver: Observer<Event<Boolean>> = mockk(relaxed = true)
        val screenObserver: Observer<ScreenState> = mockk(relaxed = true)

        every { provider.getAndCacheAllData() } returns Completable.error(loadError)

        viewModel.screenState.observeForever(screenObserver)
        viewModel.loadingError.observeForever(errorObserver)

        viewModel.loadDataFromRemote()

        verify(exactly = 1) { screenObserver.onChanged(WelcomeScreenState.INITIAL) }
        verify(exactly = 1) { screenObserver.onChanged(WelcomeScreenState.LOADING_DATA) }
        verify(exactly = 1) { provider.getAndCacheAllData() }
        verify(exactly = 1) { screenObserver.onChanged(WelcomeScreenState.ERROR) }
        verify(exactly = 1) { errorObserver.onChanged(Event(true)) }

        confirmVerified(screenObserver)
        confirmVerified(errorObserver)
        confirmVerified(provider)

        viewModel.screenState.removeObserver(screenObserver)
        viewModel.loadingError.removeObserver(errorObserver)
    }
}