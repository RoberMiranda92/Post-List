package com.robertomiranda.data

import com.robertomiranda.data.api.models.PostApi
import com.robertomiranda.data.models.Comment
import com.robertomiranda.data.models.Post
import com.robertomiranda.data.room.models.CommentRoom
import com.robertomiranda.data.room.models.PostRoom

fun PostApi.toModel(): Post {
    return Post(title, userId, id, body)
}

fun PostRoom.toModel(): Post {
    return Post(title, userId, id, body)
}

fun CommentRoom.toModel(): Comment {
    return Comment(name, postId, id, body, email)
}