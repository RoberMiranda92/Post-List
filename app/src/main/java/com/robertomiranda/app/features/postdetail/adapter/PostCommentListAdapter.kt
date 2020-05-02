package com.robertomiranda.app.features.postdetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.robertomiranda.app.core.list.BaseListAdapter
import com.robertomiranda.app.core.list.BaseViewHolder
import com.robertomiranda.app.core.list.ListItem
import com.robertomiranda.app.databinding.RowCommentBinding
import com.robertomiranda.app.databinding.RowCommentCountBinding
import com.robertomiranda.app.features.postdetail.adapter.models.CommentBundle

class PostCommentListAdapter : BaseListAdapter<ListItem>(
    DIFF_CALLBACK
) {
    override fun getViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ListItem> {
        return when (viewType) {
            CommentBundle.VIEW_TYPE_HEADER -> {
                PostCommentHeaderHolder(
                    RowCommentCountBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            CommentBundle.VIEW_TYPE_COMMENT -> {
                PostCommentViewHolder(
                    RowCommentBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> error("Invalid view type")
        } as BaseViewHolder<ListItem>

    }

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<ListItem>() {

            override fun areItemsTheSame(oldComment: ListItem, newComment: ListItem) =
                oldComment.getID() == newComment.getID()

            override fun areContentsTheSame(oldComment: ListItem, newComment: ListItem) =
                oldComment == newComment
        }
    }
}
