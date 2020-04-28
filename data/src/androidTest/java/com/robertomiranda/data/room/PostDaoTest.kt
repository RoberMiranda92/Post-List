package com.robertomiranda.data.room

import com.robertomiranda.data.BaseDaoTest
import com.robertomiranda.data.room.dao.PostsDao
import com.robertomiranda.data.room.models.PostRoom
import org.junit.Test

class PostDaoTest : BaseDaoTest() {

    private lateinit var postDao: PostsDao

    override fun setUpChild() {
        super.setUpChild()
        postDao = database.postDao()
    }

    @Test
    fun insertAndGetAllPost() {
        val postList = mutableListOf<PostRoom>(
            Post1,
            Post2
        )
        postDao.addPostList(postList).test().await()

        val testObserver = postDao.getAllPost().test()

        testObserver.awaitCount(1)
        testObserver.assertResult(postList)
        testObserver.dispose()
    }

    @Test
    fun insertAndGetPostByID() {
        val postList = mutableListOf<PostRoom>(
            Post1,
            Post2
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
    }
}

