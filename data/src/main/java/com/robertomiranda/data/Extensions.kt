package com.robertomiranda.data

import com.robertomiranda.data.api.models.CommentApi
import com.robertomiranda.data.api.models.PostApi
import com.robertomiranda.data.api.models.UserApi
import com.robertomiranda.data.models.Comment
import com.robertomiranda.data.models.Post
import com.robertomiranda.data.models.User
import com.robertomiranda.data.room.models.CommentRoom
import com.robertomiranda.data.room.models.PostRoom
import com.robertomiranda.data.room.models.UserRoom

fun PostApi.toModel(): Post {
    return Post(title, userId, id, body)
}

fun PostRoom.toModel(): Post {
    return Post(title, userId, id, body)
}

fun Post.toEntity(): PostRoom {
    return PostRoom(title, userId, id, body)
}

fun CommentRoom.toModel(): Comment {
    return Comment(name, postId, id, body, email)
}

fun CommentApi.toModel(): Comment {
    return Comment(name, postId, id, body, email)
}

fun Comment.toEntity(): CommentRoom {
    return CommentRoom(name, postId, id, body, email)
}

fun UserApi.toModel(): User {
    return User(id, username, website, phone, name, email)
}

//TODO GET CORRECT ID
fun User.toEntity(): UserRoom {
    return UserRoom(id, website, 1, phone, name, 1, email, username)
}