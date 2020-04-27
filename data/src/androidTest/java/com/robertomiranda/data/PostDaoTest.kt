package com.robertomiranda.data

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.robertomiranda.data.room.PostListDataBase
import com.robertomiranda.data.room.dao.PostsDao
import com.robertomiranda.data.room.models.PostRoom
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PostDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule() //it's just for Junit to execute tasks synchronousl

    private lateinit var database: PostListDataBase
    private lateinit var postDao: PostsDao

    @Before
    fun init() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context,
            PostListDataBase::class.java
        ).allowMainThreadQueries()
            .build()
        postDao = database.postDao()
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun insertAndGetAllPost() {
        val postList = mutableListOf<PostRoom>(Post1, Post2)
        postDao.addPostList(postList).test().await()

        val testObserver = postDao.getAllPost().test()

        testObserver.awaitCount(1)
        testObserver.assertResult(postList)
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

