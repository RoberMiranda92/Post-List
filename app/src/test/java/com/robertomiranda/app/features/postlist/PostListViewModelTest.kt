package com.robertomiranda.app.features.postlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.robertomiranda.app.RxBaseTest
import com.robertomiranda.app.core.PostListScreenState
import com.robertomiranda.app.core.ScreenState
import com.robertomiranda.app.utils.asPagedList
import com.robertomiranda.data.LocalRepository
import com.robertomiranda.data.models.Post
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Flowable
import org.junit.Rule
import org.junit.Test

class PostListViewModelTest : RxBaseTest() {

    private lateinit var viewModel: PostListViewModel
    private val localRepository: LocalRepository = mockk<LocalRepository>()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    override fun setUpChild() {
        super.setUpChild()
        viewModel = PostListViewModel(localRepository)
    }

    @Test
    fun loadPostOK() {
        val data = (0..10).map {
            Post(
                "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                if (it % 9 == 0) 1 else it % 9, it,
                "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
            )
        }.asPagedList()

        val listObserver: Observer<PagedList<Post>> = mockk(relaxed = true)
        val screenObserver: Observer<ScreenState> = mockk(relaxed = true)

        every { localRepository.getAllPostPaginated() } returns Flowable.just(data)

        viewModel.screenState.observeForever(screenObserver)
        viewModel.postList.observeForever(listObserver)
        viewModel.loadPostList()

        verify(exactly = 1) { screenObserver.onChanged(PostListScreenState.INITIAL) }
        verify(exactly = 1) { screenObserver.onChanged(PostListScreenState.LOADING_DATA) }
        verify(exactly = 1) { localRepository.getAllPostPaginated() }
        verify(exactly = 1) { listObserver.onChanged(data) }
        verify(exactly = 1) { screenObserver.onChanged(PostListScreenState.DATA_LOADED) }

        confirmVerified(screenObserver)
        confirmVerified(listObserver)

        viewModel.screenState.removeObserver(screenObserver)
        viewModel.postList.removeObserver(listObserver)
    }

    @Test
    fun loadPostEmptyOK() {
        val data = emptyList<Post>().asPagedList()

        val listObserver: Observer<PagedList<Post>> = mockk(relaxed = true)
        val screenObserver: Observer<ScreenState> = mockk(relaxed = true)

        every { localRepository.getAllPostPaginated() } returns Flowable.just(data)

        viewModel.screenState.observeForever(screenObserver)
        viewModel.postList.observeForever(listObserver)
        viewModel.loadPostList()

        verify(exactly = 1) { screenObserver.onChanged(PostListScreenState.INITIAL) }
        verify(exactly = 1) { screenObserver.onChanged(PostListScreenState.LOADING_DATA) }
        verify(exactly = 1) { localRepository.getAllPostPaginated() }
        verify(exactly = 1) { screenObserver.onChanged(PostListScreenState.EMPTY_DATA) }

        confirmVerified(screenObserver)
        confirmVerified(listObserver)

        viewModel.screenState.removeObserver(screenObserver)
        viewModel.postList.removeObserver(listObserver)
    }

    @Test
    fun loadPostKO() {
        val error = Exception("My error")

        val listObserver: Observer<PagedList<Post>> = mockk(relaxed = true)
        val screenObserver: Observer<ScreenState> = mockk(relaxed = true)

        every { localRepository.getAllPostPaginated() } returns Flowable.error(error)

        viewModel.screenState.observeForever(screenObserver)
        viewModel.postList.observeForever(listObserver)
        viewModel.loadPostList()

        verify(exactly = 1) { screenObserver.onChanged(PostListScreenState.INITIAL) }
        verify(exactly = 1) { screenObserver.onChanged(PostListScreenState.LOADING_DATA) }
        verify(exactly = 1) { localRepository.getAllPostPaginated() }
        verify(exactly = 1) { screenObserver.onChanged(PostListScreenState.ERROR) }

        confirmVerified(screenObserver)
        confirmVerified(listObserver)

        viewModel.screenState.removeObserver(screenObserver)
        viewModel.postList.removeObserver(listObserver)
    }
}