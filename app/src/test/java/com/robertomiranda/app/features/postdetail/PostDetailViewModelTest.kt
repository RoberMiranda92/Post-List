package com.robertomiranda.app.features.postdetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.robertomiranda.app.RxBaseTest
import com.robertomiranda.app.core.PostDetailScreenState
import com.robertomiranda.app.core.ScreenState
import com.robertomiranda.data.LocalRepository
import com.robertomiranda.data.models.Comment
import com.robertomiranda.data.models.Post
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Flowable
import io.reactivex.Maybe
import org.junit.Rule
import org.junit.Test

class PostDetailViewModelTest : RxBaseTest() {

    private lateinit var viewModel: PostDetailViewModel
    private val localRepository: LocalRepository = mockk<LocalRepository>()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    override fun setUpChild() {
        super.setUpChild()
        viewModel = PostDetailViewModel(localRepository)
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

        val commentsObserver: Observer<List<Comment>> = mockk(relaxed = true)
        val postDetailObserver: Observer<Post> = mockk(relaxed = true)
        val screenObserver: Observer<ScreenState> = mockk(relaxed = true)

        every { localRepository.getPostById(any()) } returns Maybe.just(post)
        every { localRepository.getAllCommentsFromPost(any()) } returns Flowable.just(comments)

        viewModel.screenState.observeForever(screenObserver)
        viewModel.postData.observeForever(postDetailObserver)
        viewModel.postCommentsData.observeForever(commentsObserver)

        viewModel.loadPostDetails(1)

        verify(exactly = 1) { screenObserver.onChanged(PostDetailScreenState.INITIAL) }
        verify(exactly = 1) { screenObserver.onChanged(PostDetailScreenState.LOADING_DATA) }
        verify(exactly = 1) { localRepository.getPostById(1) }
        verify(exactly = 1) { localRepository.getAllCommentsFromPost(1) }
        verify(exactly = 1) { postDetailObserver.onChanged(post) }
        verify(exactly = 1) { commentsObserver.onChanged(comments) }
        verify(exactly = 1) { screenObserver.onChanged(PostDetailScreenState.DATA_LOADED) }

        confirmVerified(screenObserver)
        confirmVerified(postDetailObserver)
        confirmVerified(commentsObserver)
        confirmVerified(localRepository)

        viewModel.screenState.removeObserver(screenObserver)
        viewModel.postData.removeObserver(postDetailObserver)
        viewModel.postCommentsData.removeObserver(commentsObserver)
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

        val commentsObserver: Observer<List<Comment>> = mockk(relaxed = true)
        val postDetailObserver: Observer<Post> = mockk(relaxed = true)
        val screenObserver: Observer<ScreenState> = mockk(relaxed = true)

        every { localRepository.getPostById(any()) } returns Maybe.error(postError)
        every { localRepository.getAllCommentsFromPost(any()) } returns Flowable.just(comments)

        viewModel.screenState.observeForever(screenObserver)
        viewModel.postData.observeForever(postDetailObserver)
        viewModel.postCommentsData.observeForever(commentsObserver)

        viewModel.loadPostDetails(1)

        verify(exactly = 1) { screenObserver.onChanged(PostDetailScreenState.INITIAL) }
        verify(exactly = 1) { screenObserver.onChanged(PostDetailScreenState.LOADING_DATA) }
        verify(exactly = 1) { localRepository.getPostById(1) }
        verify(exactly = 1) { localRepository.getAllCommentsFromPost(1) }
        verify(exactly = 1) { screenObserver.onChanged(PostDetailScreenState.ERROR) }

        confirmVerified(screenObserver)
        confirmVerified(postDetailObserver)
        confirmVerified(commentsObserver)
        confirmVerified(localRepository)

        viewModel.screenState.removeObserver(screenObserver)
        viewModel.postData.removeObserver(postDetailObserver)
        viewModel.postCommentsData.removeObserver(commentsObserver)
    }

}