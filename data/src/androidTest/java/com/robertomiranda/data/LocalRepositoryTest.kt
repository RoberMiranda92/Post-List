package com.robertomiranda.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.robertomiranda.data.room.dao.CommentsDao
import com.robertomiranda.data.room.dao.PostsDao
import com.robertomiranda.data.room.dao.UsersDao
import com.robertomiranda.data.room.models.CommentRoom
import com.robertomiranda.data.room.models.PostRoom
import com.robertomiranda.data.room.models.UserRoom
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LocalRepositoryTest : BaseDataBaseTest() {

    private lateinit var postDao: PostsDao
    private lateinit var commentsDao: CommentsDao
    private lateinit var usersDao: UsersDao
    private lateinit var localRepository: LocalRepository
    private lateinit var postList: List<PostRoom>
    private lateinit var commentList: List<CommentRoom>
    private lateinit var users: List<UserRoom>

    override fun setUpChild() {
        postDao = database.postDao()
        commentsDao = database.commentsDao()
        usersDao = database.usersDao()

        localRepository = LocalRepository(postDao, usersDao, commentsDao)

        insertDataBaseData()
    }

    private fun insertDataBaseData() {
        val userRange = (1..9)
        val postRange = (1..9)
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
                if(it % 9 == 0) 1 else it % 9 , it,
                "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
            )
        }

        commentList = comments.map {
            CommentRoom(
                "quo vero reiciendis velit similique earum",
                if(it % 9 == 0) 1 else it % 9,
                it,
                "est natus enim nihil est dolore omnis voluptatem numquam\net omnis occaecati quod ullam at\nvoluptatem error expedita pariatur\nnihil sint nostrum voluptatem reiciendis et",
                "Jayne_Kuhic@sydney.com"
            )
        }

        usersDao.addAll(users).test().await()
        postDao.addPostList(postList).test().await()
        commentsDao.addCommentsList(commentList).test().await()
    }

    @Test
    fun getAllPostPaginatedOK() {
        val testSubscriber = localRepository.getAllPost().test()

        testSubscriber.awaitCount(1)
        testSubscriber.assertValue { list -> list == postList.subList(0, 30).map { it.toModel() } }
            .assertNoErrors()
            .assertNotComplete()
        testSubscriber.dispose()
    }

    @Test
    fun getAllCommentsFromPost() {
        val testSubscriber = localRepository.getAllCommentsFromPost(1).test()

        val comments = commentList.filter { it.postId == 1 }.map { it.toModel() }

        testSubscriber.awaitCount(1)
        testSubscriber.assertValue { list -> list == comments }
            .assertNoErrors()
            .assertNotComplete()
        testSubscriber.dispose()
    }

    override fun tearDownChild() {
        database.clearAllTables()
    }
}