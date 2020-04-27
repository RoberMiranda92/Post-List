package com.robertomiranda.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.robertomiranda.data.room.dao.CommentsDao
import com.robertomiranda.data.room.dao.PostsDao
import com.robertomiranda.data.room.dao.UsersDao
import com.robertomiranda.data.room.models.*

@Database(
    entities = [AddressRoom::class, CommentRoom::class, CompanyRoom::class, GeoRoom::class,
        PostRoom::class, UserRoom::class], version = 1
)
abstract class PostListDataBase : RoomDatabase() {

    abstract fun postDao(): PostsDao

    abstract fun commentsDao(): CommentsDao

    abstract fun usersDao(): UsersDao

    companion object {

        @Volatile
        private var INSTANCE: PostListDataBase? = null

        fun getInstance(context: Context): PostListDataBase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                PostListDataBase::class.java, "Sample.db"
            ).fallbackToDestructiveMigration().build()
    }
}