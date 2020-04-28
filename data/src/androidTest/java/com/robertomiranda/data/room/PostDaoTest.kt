package com.robertomiranda.data.room

import androidx.room.paging.LimitOffsetDataSource
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.robertomiranda.data.BaseDataBaseTest
import com.robertomiranda.data.room.dao.PostsDao
import com.robertomiranda.data.room.models.PostRoom
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PostDaoTest : BaseDataBaseTest() {

    private lateinit var postDao: PostsDao

    override fun setUpChild() {
        super.setUpChild()
        postDao = database.postDao()
    }

    @Test
    fun insertAndGetAllPost() {

        val postList: List<PostRoom> = (1..100).map {
            PostRoom(
                "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                1, it,
                "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
            )
        }
        postDao.addPostList(postList).test().await()

        val factory = postDao.getAllPost()
        val testObserver = factory.create() as LimitOffsetDataSource
        assert(testObserver.loadRange(0, 10) == postList.subList(0, 10))
        assert(testObserver.loadRange(11, 10) == postList.subList(11, 20))

    }

    @Test
    fun insertAndGetPostByID() {
        val postList = mutableListOf<PostRoom>(
            Post1,
            Post2,
            Post3
        )
        postDao.addPostList(postList).test().await()

        val testObserver = postDao.getPostById(Post1.id).test()

        testObserver.awaitCount(1)
        testObserver.assertResult(Post1)

        testObserver.dispose()
    }

    companion object {
        val Post1 = PostRoom(
            "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
            1, 1,
            "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
        )

        val Post2 = PostRoom(
            "qui est esse",
            1, 2,
            "est rerum tempore vitae\\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\\nqui aperiam non debitis possimus qui neque nisi nulla"
        )

        val Post3 = PostRoom(
            "qui est esse",
            2, 3,
            "est rerum tempore vitae\\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\\nqui aperiam non debitis possimus qui neque nisi nulla"
        )
    }
}

