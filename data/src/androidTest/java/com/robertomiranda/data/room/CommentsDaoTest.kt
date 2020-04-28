package com.robertomiranda.data.room

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.robertomiranda.data.BaseDataBaseTest
import com.robertomiranda.data.room.dao.CommentsDao
import com.robertomiranda.data.room.models.CommentRoom
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CommentsDaoTest : BaseDataBaseTest() {

    private lateinit var commentsDao: CommentsDao

    override fun setUpChild() {
        commentsDao = database.commentsDao()
    }

    @Test
    fun insertAndGetAllPost() {
        val commentList = mutableListOf<CommentRoom>(
            Comment1,
            Comment2,
            Comment3
        )
        commentsDao.addCommentsList(commentList).test().await()

        val testObserver = commentsDao.getAllComments().test()

        testObserver.awaitCount(1)
        testObserver.assertValue(commentList)
        testObserver.dispose()
    }

    @Test
    fun insertAndGetPostByID() {
        val commentList = mutableListOf<CommentRoom>(
            Comment1,
            Comment2,
            Comment3
        )
        commentsDao.addCommentsList(commentList).test().await()

        val testObserver = commentsDao.getCommentByPostId(1).test()

        testObserver.awaitCount(1)
        testObserver.assertValue(commentList.subList(0, 2))
        testObserver.dispose()
    }

    companion object {
        val Comment1 = CommentRoom(
            "id labore ex et quam laborum",
            1,
            1,
            "laudantium enim quasi est quidem magnam voluptate ipsam eos\ntempora quo necessitatibus\ndolor quam autem quasi\nreiciendis et nam sapiente accusantium",
            "Eliseo@gardner.biz"
        )

        val Comment2 = CommentRoom(
            "quo vero reiciendis velit similique earum",
            1,
            2,
            "est natus enim nihil est dolore omnis voluptatem numquam\net omnis occaecati quod ullam at\nvoluptatem error expedita pariatur\nnihil sint nostrum voluptatem reiciendis et",
            "Jayne_Kuhic@sydney.com"
        )

        val Comment3 = CommentRoom(
            "quo vero reiciendis velit similique earum",
            2,
            3,
            "est natus enim nihil est dolore omnis voluptatem numquam\net omnis occaecati quod ullam at\nvoluptatem error expedita pariatur\nnihil sint nostrum voluptatem reiciendis et",
            "Jayne_Kuhic@sydney.com"
        )
    }
}

