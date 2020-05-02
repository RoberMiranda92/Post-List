package com.robertomiranda.app.features.postdetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.robertomiranda.app.databinding.RowPostBinding
import com.robertomiranda.data.models.Comment
import com.robertomiranda.data.models.Post

class PostComentListAdapter : PagedListAdapter<Post, PostViewHolder>(
    DIFF_CALLBACK
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            RowPostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post: Post? = getItem(position)

        post?.let {
            holder.bind(it)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<Comment>() {

            override fun areItemsTheSame(oldComment: Comment, newComment: Comment) = oldComment.id == newComment.id

            override fun areContentsTheSame(oldComment: Comment, newComment: Comment) = oldComment == newComment
        }

        @JvmStatic
        @BindingAdapter("items")
        fun RecyclerView.bindItems(post: List<Comment>?) {
            post?.run {
                val adapter = adapter as PostComentListAdapter
                adapter.submitList(this)
            }
        }
    }
}
