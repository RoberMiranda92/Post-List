package com.robertomiranda.data.room

import android.content.Context

class DaoFactory {

    companion object {
        fun postDao(context: Context) = PostListDataBase.getInstance(context).postDao()

        fun commentsDao(context: Context) = PostListDataBase.getInstance(context).commentsDao()

        fun usersDao(context: Context) = PostListDataBase.getInstance(context).usersDao()

        fun resourcesDao(context: Context) = PostListDataBase.getInstance(context).resourcesDao()
    }
}