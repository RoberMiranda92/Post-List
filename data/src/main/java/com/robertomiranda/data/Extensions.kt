package com.robertomiranda.data

import com.robertomiranda.data.api.models.PostApi
import com.robertomiranda.data.room.models.PostRoom

fun PostApi.toModel(): Post {
    return Post(name, userId, id, body, email)
}

fun PostRoom.toModel(): Post {
    return Post(name, userId, id, body, email)
}