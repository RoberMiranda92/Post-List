package com.robertomiranda.app.features.postdetail.adapter.models

import com.robertomiranda.app.core.list.ListItem
import com.robertomiranda.data.models.Comment

data class CommentListItem(val comment: Comment) : ListItem {

    override fun getType(): Int = CommentBundle.VIEW_TYPE_COMMENT

    override fun getID(): Int {
        TODO("Not yet implemented")
    }

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