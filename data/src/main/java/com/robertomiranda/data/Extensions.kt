package com.robertomiranda.data

import android.net.Uri
import com.robertomiranda.data.api.models.CommentApi
import com.robertomiranda.data.api.models.PostApi
import com.robertomiranda.data.api.models.ResourceApi
import com.robertomiranda.data.api.models.UserApi
import com.robertomiranda.data.models.Comment
import com.robertomiranda.data.models.Post
import com.robertomiranda.data.models.Resource
import com.robertomiranda.data.models.User
import com.robertomiranda.data.room.models.CommentRoom
import com.robertomiranda.data.room.models.PostRoom
import com.robertomiranda.data.room.models.ResourceRoom
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

fun UserRoom.toModel(): User {
    return User(id, username, website, phone, name, email)
}

//TODO GET CORRECT ID
fun User.toEntity(): UserRoom {
    return UserRoom(id, website, 1, phone, name, 1, email, username)
}

fun User?.getAvatarUrl(): String {
    return this?.run {
        val builder: Uri.Builder = Uri.Builder()
        builder.scheme(Constants.AVATAR_SCHEMA)
            .authority(Constants.AVATAR_URL)
            .appendEncodedPath(Constants.AVATAER_API_PATH)
            .appendQueryParameter("name", username)
        builder.build().toString()
    } ?: ""
}

fun ResourceApi.toModel(): Resource {
    return Resource(key, value)
}

fun ResourceRoom.toModel(): Resource {
    return Resource(key, value)
}

fun Resource.toEntity(): ResourceRoom {
    return ResourceRoom(key, value)
}