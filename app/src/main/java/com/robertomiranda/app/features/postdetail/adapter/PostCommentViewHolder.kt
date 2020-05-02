package com.robertomiranda.app.features.postdetail.adapter

import com.robertomiranda.app.core.list.BaseViewHolder
import com.robertomiranda.app.databinding.RowCommentBinding
import com.robertomiranda.app.features.postdetail.adapter.models.CommentListItem

class PostCommentViewHolder(var binding: RowCommentBinding) :
    BaseViewHolder<CommentListItem>(binding.root) {

    override fun bind(item: CommentListItem) {
        with(binding) {
            email.text = item.comment.email
            title.text = item.comment.name
            body.text = item.comment.body
        }
    }
}
