package com.robertomiranda.app.features.postdetail.data

import com.robertomiranda.app.features.postdetail.data.model.PostDetail
import com.robertomiranda.data.LocalRepository
import com.robertomiranda.data.models.Comment
import com.robertomiranda.data.models.Post
import com.robertomiranda.data.models.User
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Flowable
import io.reactivex.Single
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
        val postDetail = PostDetail.PostDetailBuilder().setPost(POST)
            .setComments(COMMENT)
            .setUser(USER).build()

        every { localRepository.getPostById(any()) } returns Single.just(POST)
        every { localRepository.getUserById(any()) } returns Single.just(USER)
        every { localRepository.getAllCommentsFromPost(any()) } returns Flowable.just(COMMENT)

        val testObserver = provider.getPostDetail(1).test()

        testObserver.awaitCount(1)
            .assertResult(postDetail)

        verify(exactly = 1) { localRepository.getPostById(1) }
        verify(exactly = 1) { localRepository.getUserById(1) }
        verify(exactly = 1) { localRepository.getAllCommentsFromPost(1) }

        confirmVerified(localRepository)
    }

    @Test
    fun loadPostPostIdKO() {
        val postError = Error("My error")

        every { localRepository.getPostById(any()) } returns Single.error(postError)
        every { localRepository.getUserById(any()) } returns Single.just(USER)
        every { localRepository.getAllCommentsFromPost(any()) } returns Flowable.just(COMMENT)

        val testObserver = provider.getPostDetail(1).test()

        testObserver
            .assertSubscribed()
            .assertError(postError)

        verify(exactly = 1) { localRepository.getPostById(1) }
        verify(exactly = 0) { localRepository.getUserById(1) }
        verify(exactly = 1) { localRepository.getAllCommentsFromPost(1) }

        confirmVerified(localRepository)
    }

    @Test
    fun loadPostOKButCommentsKO() {
        val commentErrorDefaultResponse = PostDetailProvider.ERROR_LIST
        val commentError = Error("My error")

        val postDetail = PostDetail.PostDetailBuilder().setPost(POST)
            .setComments(commentErrorDefaultResponse)
            .setUser(USER).build()

        every { localRepository.getPostById(any()) } returns Single.just(POST)
        every { localRepository.getUserById(any()) } returns Single.just(USER)
        every { localRepository.getAllCommentsFromPost(any()) } returns Flowable.error(commentError)

        val testObserver = provider.getPostDetail(1).test()

        testObserver.awaitCount(1)
            .assertResult(postDetail)

        verify(exactly = 1) { localRepository.getPostById(1) }
        verify(exactly = 1) { localRepository.getUserById(1) }
        verify(exactly = 1) { localRepository.getAllCommentsFromPost(1) }

        confirmVerified(localRepository)
    }

    @Test
    fun loadPostPostAndCommentOKbutUserKO() {
        val userError = Error("My error")

        every { localRepository.getPostById(any()) } returns Single.just(POST)
        every { localRepository.getUserById(any()) } returns Single.error(userError)
        every { localRepository.getAllCommentsFromPost(any()) } returns Flowable.just(COMMENT)

        val testObserver = provider.getPostDetail(1).test()

        testObserver
            .assertSubscribed()
            .assertError(userError)

        verify(exactly = 1) { localRepository.getPostById(1) }
        verify(exactly = 1) { localRepository.getAllCommentsFromPost(1) }
        verify(exactly = 1) { localRepository.getUserById(1) }

        confirmVerified(localRepository)
    }

    companion object {
        val POST: Post = Post(
            "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
            1, 1,
            "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
        )

        val USER: User = User(
            1, "Bret", "hildegard.org", "1-770-736-8031 x56442",
            "Romaguera-Crona",
            "Sincere@april.biz"
        )

        val COMMENT: List<Comment> = (0..10).map {
            Comment(
                "quo vero reiciendis velit similique earum",
                1,
                it,
                "est natus enim nihil est dolore omnis voluptatem numquam\net omnis occaecati quod ullam at\nvoluptatem error expedita pariatur\nnihil sint nostrum voluptatem reiciendis et",
                "Jayne_Kuhic@sydney.com"
            )
        }
    }
}