package com.robertomiranda.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.robertomiranda.data.repository.local.LocalRepository
import com.robertomiranda.data.room.dao.CommentsDao
import com.robertomiranda.data.room.dao.PostsDao
import com.robertomiranda.data.room.dao.ResourcesDao
import com.robertomiranda.data.room.dao.UsersDao
import com.robertomiranda.data.room.models.CommentRoom
import com.robertomiranda.data.room.models.PostRoom
import com.robertomiranda.data.room.models.ResourceRoom
import com.robertomiranda.data.room.models.UserRoom
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LocalRepositoryTest : BaseDataBaseTest() {

    private lateinit var postDao: PostsDao
    private lateinit var commentsDao: CommentsDao
    private lateinit var usersDao: UsersDao
    private lateinit var resoucesDao: ResourcesDao
    private lateinit var localRepository: LocalRepository
    private lateinit var postList: List<PostRoom>
    private lateinit var commentList: List<CommentRoom>
    private lateinit var users: List<UserRoom>
    private lateinit var resources: List<ResourceRoom>

    override fun setUpChild() {
        postDao = database.postDao()
        commentsDao = database.commentsDao()
        usersDao = database.usersDao()
        resoucesDao = database.resourcesDao()

        localRepository =
            LocalRepository(
                postDao,
                usersDao,
                commentsDao,
                resoucesDao
            )

        insertDataBaseData()
    }

    private fun insertDataBaseData() {
        val userRange = (1..9)
        val postRange = (1..100)
        val comments = (1..100)

        users = userRange.map {
            UserRoom(
                it, "hildegard.org", 1, "${it}-770-73${it}-8031 x5${it}442",
                "Romaguera-Crona",
                it * 100, "Sincere@april.${it}biz", "${it}Bret"
            )
        }

        postList = postRange.map {
            PostRoom(
                "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                if (it % 9 == 0) 1 else it % 9, it,
                "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
            )
        }

        commentList = comments.map {
            CommentRoom(
                "quo vero reiciendis velit similique earum",
                if (it % 9 == 0) 1 else it % 9,
                it,
                "est natus enim nihil est dolore omnis voluptatem numquam\net omnis occaecati quod ullam at\nvoluptatem error expedita pariatur\nnihil sint nostrum voluptatem reiciendis et",
                "Jayne_Kuhic@sydney.com"
            )
        }

        resources = listOf<ResourceRoom>(
            ResourceRoom(".info", "ℹ️"),
            ResourceRoom(".co.uk", "\uD83C\uDDEC\uD83C\uDDE7")
        )

        usersDao.addAll(users).test().await()
        postDao.addPostList(postList).test().await()
        commentsDao.addCommentsList(commentList).test().await()
        resoucesDao.addAll(resources).test().await()
    }

    @Test
    fun getAllPostPaginatedOK() {
        val testSubscriber = localRepository.getAllPostPaginated().test()

        //PagedList.Config.Builder.DEFAULT_INITIAL_PAGE_MULTIPLIER = 3
        val subList =
            postList.subList(0, LocalRepository.PAGINATION_SIZE.times(3)).map { it.toModel() }

        testSubscriber.awaitCount(1)
        testSubscriber.assertValue { list -> list.snapshot().filterNotNull() == subList }
            .assertNoErrors()
            .assertNotComplete()
        testSubscriber.dispose()
    }

    @Test
    fun getAllCommentsFromPostOK() {
        val testSubscriber = localRepository.getAllCommentsFromPost(1).test()

        val comments = commentList.filter { it.postId == 1 }.map { it.toModel() }

        testSubscriber.awaitCount(1)
        testSubscriber.assertValue { list -> list == comments }
            .assertNoErrors()
            .assertNotComplete()
        testSubscriber.dispose()
    }

    @Test
    fun getUserFromPostOK() {
        val testSubscriber = localRepository.getUserById(1).test()

        val user = users.first { it.id == 1 }.toModel()

        testSubscriber.awaitCount(1)
        testSubscriber.assertResult(user)
        testSubscriber.dispose()
    }

    @Test
    fun getAllResources() {
        val testSubscriber = localRepository.getAllResources().test()

        val resources = resources.map { it.toModel() }

        testSubscriber.awaitCount(1)
        testSubscriber.assertValue { list -> list == resources }
            .assertNoErrors()
            .assertNotComplete()
        testSubscriber.dispose()
    }

    override fun tearDownChild() {
        database.clearAllTables()
    }
}