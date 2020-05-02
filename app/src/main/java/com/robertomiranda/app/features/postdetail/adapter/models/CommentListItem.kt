package com.robertomiranda.app.features.postdetail.adapter

import com.robertomiranda.app.core.list.ListItem
import com.robertomiranda.data.models.Comment

data class CommentListItem(val comment: Comment) : ListItem {

    override fun getType(): Int = CommentBundle.VIEW_TYPE_COMMENT

    override fun equals(other: Any?): Boolean {
        return if (other is CommentListItem) {
            other.comment == this.comment
        } else {
            false
        }
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}