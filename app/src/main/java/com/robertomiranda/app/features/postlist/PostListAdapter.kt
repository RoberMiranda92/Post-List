package com.robertomiranda.app.features.postlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.robertomiranda.app.databinding.RowPostBinding
import com.robertomiranda.data.models.Post

class PostListAdapter : PagedListAdapter<Post, PostViewHolder>(DIFF_CALLBACK) {

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
            DiffUtil.ItemCallback<Post>() {

            override fun areItemsTheSame(oldPost: Post, newPost: Post) = oldPost.id == newPost.id

            override fun areContentsTheSame(oldPost: Post, newPost: Post) = oldPost == newPost
        }

        @JvmStatic
        @BindingAdapter("items")
        fun RecyclerView.bindItems(post: PagedList<Post>?) {
            post?.run {
                val adapter = adapter as PostListAdapter
                adapter.submitList(this)
            }
        }
    }
}
