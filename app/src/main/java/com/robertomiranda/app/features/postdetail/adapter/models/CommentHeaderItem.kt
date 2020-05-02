package com.robertomiranda.app.features.postdetail.adapter.models

import com.robertomiranda.app.core.list.ListItem

class CommentHeaderItem(val size: Int) : ListItem {

    override fun getType(): Int = CommentBundle.VIEW_TYPE_HEADER

    override fun getID(): Int = -1

    override fun equals(other: Any?): Boolean {
        return if (other is CommentHeaderItem) {
            other.getType() == this.getType()
        } else {
            false
        }
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}