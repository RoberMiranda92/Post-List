package com.robertomiranda.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.robertomiranda.data.room.models.ResourceRoom
import io.reactivex.Flowable
import io.reactivex.Maybe

@Dao
interface ResourcesDao {

    @Query("SELECT * FROM resources")
    fun getAllResources(): Flowable<List<ResourceRoom>>

    fun addAll(resources: List<ResourceRoom>): Maybe<List<Long>>
}