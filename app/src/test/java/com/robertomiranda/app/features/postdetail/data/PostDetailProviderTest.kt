package com.robertomiranda.app.features.postdetail.data

import com.robertomiranda.data.LocalRepository
import com.robertomiranda.data.models.Comment
import com.robertomiranda.data.models.Post
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Flowable
import io.reactivex.Maybe
import org.junit.Before
import org.junit.Test

class PostDetailProviderTest {

    private lateinit var provider: PostDetailProvider
    private val localRepository: LocalRepository = mockk()

    @Before
    fun setUp() {
        provider = PostDetailProvider(localRepository)
    }

    @Test
    fun loadPostOK() {
        val post = Post(
            "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
            1, 1,
            "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
        )
        val comments = (0..10).map {
            Comment(
                "quo vero reiciendis velit similique earum",
                1,
                it,
                "est natus enim nihil est dolore omnis voluptatem numquam\net omnis occaecati quod ullam at\nvoluptatem error expedita pariatur\nnihil sint nostrum voluptatem reiciendis et",
                "Jayne_Kuhic@sydney.com"
            )
        }
        every { localRepository.getPostById(any()) } returns Maybe.just(post)
        every { localRepository.getAllCommentsFromPost(any()) } returns Flowable.just(comments)

        val testObserver = provider.getPostDetail(1).test()

        testObserver.awaitCount(1)
            .assertResult(Pair(post, comments))

        verify(exactly = 1) { localRepository.getPostById(1) }
        verify(exactly = 1) { localRepository.getAllCommentsFromPost(1) }

        confirmVerified(localRepository)
    }

    @Test
    fun loadPostPostIdKO() {
        val postError = Error("My error")
        val comments = (0..10).map {
            Comment(
                "quo vero reiciendis velit similique earum",
                1,
                it,
                "est natus enim nihil est dolore omnis voluptatem numquam\net omnis occaecati quod ullam at\nvoluptatem error expedita pariatur\nnihil sint nostrum voluptatem reiciendis et",
                "Jayne_Kuhic@sydney.com"
            )
        }

        every { localRepository.getPostById(any()) } returns Maybe.error(postError)
        every { localRepository.getAllCommentsFromPost(any()) } returns Flowable.just(comments)

        val testObserver = provider.getPostDetail(1).test()

        testObserver
            .assertSubscribed()
            .assertError(postError)

        verify(exactly = 1) { localRepository.getPostById(1) }
        verify(exactly = 1) { localRepository.getAllCommentsFromPost(1) }

        confirmVerified(localRepository)
    }

    @Test
    fun loadPostOKButErrorKO() {
        val post = Post(
            "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
            1, 1,
            "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
        )
        val commentErrorDefaultResponse = PostDetailProvider.ERROR_LIST
        val commentError = Error("My error")

        every { localRepository.getPostById(any()) } returns Maybe.just(post)
        every { localRepository.getAllCommentsFromPost(any()) } returns Flowable.error(commentError)

        val testObserver = provider.getPostDetail(1).test()

        testObserver.awaitCount(1)
            .assertResult(Pair(post, commentErrorDefaultResponse))

        verify(exactly = 1) { localRepository.getPostById(1) }
        verify(exactly = 1) { localRepository.getAllCommentsFromPost(1) }

        confirmVerified(localRepository)
    }
}