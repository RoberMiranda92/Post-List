package com.robertomiranda.app.features.postlist.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.robertomiranda.app.utils.asPagedList
import com.robertomiranda.data.LocalRepository
import com.robertomiranda.data.models.Post
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PostListProviderTest {

    private lateinit var provider: PostListProvider
    private val localRepository: LocalRepository = mockk()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        provider = PostListProvider(localRepository)
    }

    @Test
    fun loadAllPostPaginatedOK() {
        val post = emptyList<Post>().asPagedList()

        every { localRepository.getAllPostPaginated() } returns Flowable.just(post)

        val testObserver = provider.getAllPostPaginated().test()

        testObserver.awaitCount(1)
            .assertResult(post)

        verify(exactly = 1) { localRepository.getAllPostPaginated() }

        confirmVerified(localRepository)
    }

    @Test
    fun loadPostPostIdKO() {
        val postError = Error("My error")

        every { localRepository.getAllPostPaginated() } returns Flowable.error(postError)

        val testObserver = provider.getAllPostPaginated().test()

        testObserver
            .assertSubscribed()
            .assertError(postError)

        verify(exactly = 1) { localRepository.getAllPostPaginated() }

        confirmVerified(localRepository)
    }
}