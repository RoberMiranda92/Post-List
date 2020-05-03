package com.robertomiranda.app.features.welcome.data

import com.robertomiranda.data.repository.local.LocalRepository
import com.robertomiranda.data.repository.remote.RemoteRepository
import com.robertomiranda.data.models.Comment
import com.robertomiranda.data.models.Post
import com.robertomiranda.data.models.Resource
import com.robertomiranda.data.models.User
import com.robertomiranda.data.room.models.ResourceRoom
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Flowable
import io.reactivex.Maybe
import org.junit.Before
import org.junit.Test

class WelcomeDataProviderTest {

    private lateinit var welcomeDataProvider: WelcomeDataProvider
    private val localRepository = mockk<LocalRepository>()
    private val remoteRepository = mockk<RemoteRepository>()

    @Before
    fun setUp() {
        welcomeDataProvider = WelcomeDataProvider(remoteRepository, localRepository)
    }

    @Test
    fun getAllDataOk() {
        val postsList: List<Post> = listOf(POST)
        val commentsList: List<Comment> = listOf(COMMENT)
        val userList: List<User> = listOf(USER)
        val resources:List<Resource> = RESOUCES

        every { remoteRepository.getAllPost() } returns Flowable.just(postsList)
        every { remoteRepository.getAllComments() } returns Flowable.just(commentsList)
        every { remoteRepository.geAllUsers() } returns Flowable.just(userList)
        every { remoteRepository.getAllResources() } returns Flowable.just(resources)

        every { localRepository.addAllPost(any()) } returns Maybe.just(emptyList())
        every { localRepository.addAllComments(any()) } returns Maybe.just(emptyList())
        every { localRepository.addAllUsers(any()) } returns Maybe.just(emptyList())
        every { localRepository.addAllResources(any()) } returns Maybe.just(emptyList())

        val testObserver = welcomeDataProvider.getAndCacheAllData().test()

        testObserver
            .assertSubscribed()
            .assertComplete()
            .assertNoErrors()

        verify(exactly = 1) { remoteRepository.getAllPost() }
        verify(exactly = 1) { remoteRepository.getAllComments() }
        verify(exactly = 1) { remoteRepository.geAllUsers() }
        verify(exactly = 1) { remoteRepository.getAllResources() }
        verify(exactly = 1) { localRepository.addAllPost(postsList) }
        verify(exactly = 1) { localRepository.addAllComments(commentsList) }
        verify(exactly = 1) { localRepository.addAllUsers(userList) }
        verify(exactly = 1) { localRepository.addAllResources(resources) }

        confirmVerified(remoteRepository)
        confirmVerified(localRepository)
    }

    @Test
    fun getAllDataErrorOnGetAllPost() {
        val postError = Error("my error")

        every { remoteRepository.getAllPost() } returns Flowable.error(postError)

        val testObserver = welcomeDataProvider.getAndCacheAllData().test()

        testObserver
            .assertSubscribed()
            .assertError(postError)

        verify(exactly = 1) { remoteRepository.getAllPost() }

        confirmVerified(remoteRepository)
        confirmVerified(localRepository)
    }

    @Test
    fun getAllDataErrorOnSaveAllPost() {
        val postsList: List<Post> = listOf(POST)
        val postError = Error("my error")

        every { remoteRepository.getAllPost() } returns Flowable.just(postsList)
        every { localRepository.addAllPost(any()) } returns Maybe.error(postError)

        val testObserver = welcomeDataProvider.getAndCacheAllData().test()

        testObserver
            .assertSubscribed()
            .assertError(postError)

        verify(exactly = 1) { remoteRepository.getAllPost() }
        verify(exactly = 1) { localRepository.addAllPost(postsList) }

        confirmVerified(remoteRepository)
        confirmVerified(localRepository)
    }

    @Test
    fun getAllDataErrorOnGetAllComments() {
        val postsList: List<Post> = listOf(POST)
        val commentError = Error("my error")

        every { remoteRepository.getAllPost() } returns Flowable.just(postsList)
        every { remoteRepository.getAllComments() } returns Flowable.error(commentError)

        every { localRepository.addAllPost(any()) } returns Maybe.just(emptyList())

        val testObserver = welcomeDataProvider.getAndCacheAllData().test()

        testObserver
            .assertSubscribed()
            .assertError(commentError)

        verify(exactly = 1) { remoteRepository.getAllPost() }
        verify(exactly = 1) { remoteRepository.getAllComments() }
        verify(exactly = 1) { localRepository.addAllPost(postsList) }

        confirmVerified(remoteRepository)
        confirmVerified(localRepository)
    }

    @Test
    fun getAllDataErrorOnSaveAllComments() {
        val postsList: List<Post> = listOf(POST)
        val commentsList: List<Comment> = listOf(COMMENT)
        val commentError = Error("my error")

        every { remoteRepository.getAllPost() } returns Flowable.just(postsList)
        every { remoteRepository.getAllComments() } returns Flowable.just(commentsList)

        every { localRepository.addAllPost(any()) } returns Maybe.just(emptyList())
        every { localRepository.addAllComments(any()) } returns Maybe.error(commentError)

        val testObserver = welcomeDataProvider.getAndCacheAllData().test()

        testObserver
            .assertSubscribed()
            .assertError(commentError)

        verify(exactly = 1) { remoteRepository.getAllPost() }
        verify(exactly = 1) { remoteRepository.getAllComments() }
        verify(exactly = 1) { localRepository.addAllPost(postsList) }
        verify(exactly = 1) { localRepository.addAllComments(commentsList) }

        confirmVerified(remoteRepository)
        confirmVerified(localRepository)
    }

    @Test
    fun getAllDataErrorOnGetAllUsers() {
        val postsList: List<Post> = listOf(POST)
        val commentsList: List<Comment> = listOf(COMMENT)
        val userError = Error("my error")

        every { remoteRepository.getAllPost() } returns Flowable.just(postsList)
        every { remoteRepository.getAllComments() } returns Flowable.just(commentsList)
        every { remoteRepository.geAllUsers() } returns Flowable.error(userError)

        every { localRepository.addAllPost(any()) } returns Maybe.just(emptyList())
        every { localRepository.addAllComments(any()) } returns Maybe.just(emptyList())

        val testObserver = welcomeDataProvider.getAndCacheAllData().test()

        testObserver
            .assertSubscribed()
            .assertError(userError)

        verify(exactly = 1) { remoteRepository.getAllPost() }
        verify(exactly = 1) { remoteRepository.getAllComments() }
        verify(exactly = 1) { remoteRepository.geAllUsers() }
        verify(exactly = 1) { localRepository.addAllPost(postsList) }
        verify(exactly = 1) { localRepository.addAllComments(commentsList) }

        confirmVerified(remoteRepository)
        confirmVerified(localRepository)
    }

    @Test
    fun getAllDataErrorOnSaveAllUsers() {
        val postsList: List<Post> = listOf(POST)
        val commentsList: List<Comment> = listOf(COMMENT)
        val userList: List<User> = listOf(USER)
        val userError = Error("my error")

        every { remoteRepository.getAllPost() } returns Flowable.just(postsList)
        every { remoteRepository.getAllComments() } returns Flowable.just(commentsList)
        every { remoteRepository.geAllUsers() } returns Flowable.just(userList)

        every { localRepository.addAllPost(any()) } returns Maybe.just(emptyList())
        every { localRepository.addAllComments(any()) } returns Maybe.just(emptyList())
        every { localRepository.addAllUsers(any()) } returns Maybe.error(userError)

        val testObserver = welcomeDataProvider.getAndCacheAllData().test()

        testObserver
            .assertSubscribed()
            .assertError(userError)

        verify(exactly = 1) { remoteRepository.getAllPost() }
        verify(exactly = 1) { remoteRepository.getAllComments() }
        verify(exactly = 1) { remoteRepository.geAllUsers() }
        verify(exactly = 1) { localRepository.addAllPost(postsList) }
        verify(exactly = 1) { localRepository.addAllComments(commentsList) }
        verify(exactly = 1) { localRepository.addAllUsers(userList) }

        confirmVerified(remoteRepository)
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

        val COMMENT: Comment = Comment(
            "quo vero reiciendis velit similique earum",
            1,
            1,
            "est natus enim nihil est dolore omnis voluptatem numquam\net omnis occaecati quod ullam at\nvoluptatem error expedita pariatur\nnihil sint nostrum voluptatem reiciendis et",
            "Jayne_Kuhic@sydney.com"
        )

        val RESOUCES: List<Resource> = listOf<Resource>(
            Resource(".info", "ℹ️"),
            Resource(".co.uk", "\uD83C\uDDEC\uD83C\uDDE7")
        )
    }
}