package com.robertomiranda.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.robertomiranda.data.room.models.PostRoom
import com.robertomiranda.data.room.models.UserRoom
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface UsersDao {

    @Query("SELECT * FROM users")
    fun getAllUsers(): Maybe<List<UserRoom>>

    @Query("SELECT * FROM users WHERE id = :id")
    fun getUserById(id: Int): Single<UserRoom>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAll(usersList: List<UserRoom>): Maybe<List<Long>>
}