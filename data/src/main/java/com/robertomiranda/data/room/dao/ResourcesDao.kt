package com.robertomiranda.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.robertomiranda.data.room.models.ResourceRoom
import io.reactivex.Flowable
import io.reactivex.Maybe

@Dao
interface ResourcesDao {

    @Query("SELECT * FROM resources")
    fun getAllResources(): Flowable<List<ResourceRoom>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAll(resources: List<ResourceRoom>): Maybe<List<Long>>

    @Query("SELECT * FROM resources WHERE resourceKey LIKE :key")
    fun getResourceByKey(key: String): Maybe<ResourceRoom>
}