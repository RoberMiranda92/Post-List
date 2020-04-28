package com.robertomiranda.data

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.robertomiranda.data.room.PostListDataBase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
abstract class BaseDataBaseTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var database: PostListDataBase

    @Before
    open fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context,
            PostListDataBase::class.java
        ).allowMainThreadQueries()
            .build()

        setUpChild()
    }

    open fun setUpChild() {
        // override if needed
    }

    open fun tearDownChild() {
        // Override if needed
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        tearDownChild()
        database.close()
    }
}