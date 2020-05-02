package com.robertomiranda.app.features.postdetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.robertomiranda.app.databinding.RowCommentBinding
import com.robertomiranda.data.models.Comment

class PostCommentListAdapter : ListAdapter<Comment, PostCommentViewHolder>(
    DIFF_CALLBACK
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostCommentViewHolder {
        return PostCommentViewHolder(
            RowCommentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PostCommentViewHolder, position: Int) {
        val comment: Comment? = getItem(position)

        comment?.let {
            holder.bind(it)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<Comment>() {

            override fun areItemsTheSame(oldComment: Comment, newComment: Comment) =
                oldComment.id == newComment.id

            override fun areContentsTheSame(oldComment: Comment, newComment: Comment) =
                oldComment == newComment
        }

        @JvmStatic
        @BindingAdapter("items")
        fun RecyclerView.bindItems(comments: List<Comment>?) {
            comments?.run {
                val adapter = adapter as PostCommentListAdapter
                adapter.submitList(this)
            }
        }
    }
}
