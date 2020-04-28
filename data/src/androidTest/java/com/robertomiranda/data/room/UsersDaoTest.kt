package com.robertomiranda.data.room

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.robertomiranda.data.BaseDataBaseTest
import com.robertomiranda.data.room.dao.UsersDao
import com.robertomiranda.data.room.models.UserRoom
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UsersDaoTest : BaseDataBaseTest() {

    private lateinit var usersDao: UsersDao

    override fun setUpChild() {
        super.setUpChild()
        usersDao = database.usersDao()
    }

    @Test
    fun insertAndGetAllUsers() {
        val usersList = mutableListOf<UserRoom>(
            User1,
            User2
        )
        usersDao.addAll(usersList).test().await()

        val testObserver = usersDao.getAllUsers().test()

        testObserver.awaitCount(1)
        testObserver.assertResult(usersList)
        testObserver.dispose()
    }

    @Test
    fun insertAndGetPostByID() {
        val usersList = mutableListOf<UserRoom>(
            User1,
            User2
        )
        usersDao.addAll(usersList).test().await()

        val testObserver = usersDao.getUserById(1).test()

        testObserver.awaitCount(1)
        testObserver.assertResult(User1)
        testObserver.dispose()
    }

    companion object {

        val User1 = UserRoom(
            1, "hildegard.org", 1, "1-770-736-8031 x56442",
            "Romaguera-Crona",
            1, "Sincere@april.biz", "Bret"
        )

        val User2 = UserRoom(
            2, "anastasia.net", 2, "010-692-6593 x09125",
            "Ervin Howell",
            2, "Shanna@melissa.tv", "Antonette"
        )
    }
}

