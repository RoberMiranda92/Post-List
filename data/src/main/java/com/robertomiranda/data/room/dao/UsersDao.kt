package com.robertomiranda.data.room.dao

import androidx.room.Query
import com.robertomiranda.data.room.models.PostRoom
import com.robertomiranda.data.room.models.UserRoom
import io.reactivex.Maybe

interface UsersDao {

    @Query("SELECT * FROM posts")
    fun getAllPost(): Maybe<List<UserRoom>>

    @Query("SELECT * FROM users WHERE id = :id")
    fun getUserById(id: String): Maybe<UserRoom>
}