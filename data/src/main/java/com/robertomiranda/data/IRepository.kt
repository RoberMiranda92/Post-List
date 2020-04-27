package com.robertomiranda.data

interface IRepository {

    fun getAllPost()

    fun getPostById(id: String)

    fun getAllCommentsFromPost(postId: String)

    fun getUserById(id: String)

    fun getAllPostFromUser(userID: String)

    fun getAllCommentsFromUser(userID: String)
}