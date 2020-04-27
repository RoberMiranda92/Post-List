package com.robertomiranda.data

import com.robertomiranda.data.api.models.PostApi
import com.robertomiranda.data.models.Post
import com.robertomiranda.data.room.models.PostRoom

fun PostApi.toModel(): Post {
    return Post(title, userId, id, body)
}

fun PostRoom.toModel(): Post {
    return Post(title, userId, id, body)
}