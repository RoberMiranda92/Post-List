package com.robertomiranda.app.features.postdetail.data.model

import com.robertomiranda.data.models.Comment
import com.robertomiranda.data.models.Post
import com.robertomiranda.data.models.User

class PostDetail private constructor() {

    var post: Post? = null
        private set
    var user: User? = null
        private set
    var comments: List<Comment>? = null
        private set

    private constructor(builder: PostDetailBuilder) : this() {
        this.post = builder.post
        this.user = builder.user
        this.comments = builder.comments
    }

    class PostDetailBuilder {

        var post: Post? = null
            private set
        var user: User? = null
            private set
        var comments: List<Comment>? = null
            private set

        fun setPost(post: Post) = apply { this.post = post }

        fun setUser(user: User) = apply { this.user = user }

        fun setComments(comments: List<Comment>) = apply { this.comments = comments }

        fun build() =
            PostDetail(this)
    }

    override fun equals(other: Any?): Boolean {
        return if (other is PostDetail) {
            this.post == other.post && this.comments == other.comments && this.user == other.user
        } else {
            false
        }
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}