package com.robertomiranda.app.features.postdetail.adapter

import com.robertomiranda.app.R
import com.robertomiranda.app.core.list.BaseViewHolder
import com.robertomiranda.app.databinding.RowCommentCountBinding
import com.robertomiranda.app.features.postdetail.adapter.models.CommentHeaderItem

class PostCommentHeaderHolder(var binding: RowCommentCountBinding) :
    BaseViewHolder<CommentHeaderItem>(binding.root) {

    override fun bind(item: CommentHeaderItem) {
        with(binding) {
            title.text = root.context.resources.getQuantityString(
                R.plurals.post_detail_comment_total,
                item.size,
                item.size
            )
        }
    }
}
