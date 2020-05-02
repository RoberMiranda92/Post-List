package com.robertomiranda.app.features.postdetail.adapter

import com.robertomiranda.app.core.list.ListItem
import com.robertomiranda.data.models.Comment

class CommentBundle(dataSet: MutableList<Comment>) {

    private val all: MutableList<ListItem> = mutableListOf()

    init {
        if (dataSet.isNotEmpty()) {
            all.add(CommentHeaderItem())
            all.addAll(dataSet.map { CommentListItem(it) })
        }
    }

    override fun equals(other: Any?): Boolean {
        return if (other is CommentBundle) {
            other.all == this.all
        } else {
            false
        }
    }

    override fun hashCode(): Int {
        return all.hashCode()
    }

    companion object {
        const val VIEW_TYPE_COMMENT = 0
        const val VIEW_TYPE_HEADER = 1
    }
}