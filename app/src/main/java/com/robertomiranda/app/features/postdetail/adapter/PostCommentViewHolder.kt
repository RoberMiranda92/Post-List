package com.robertomiranda.app.features.postdetail.adapter

import androidx.recyclerview.widget.RecyclerView
import com.robertomiranda.app.databinding.RowCommentBinding
import com.robertomiranda.app.databinding.RowPostBinding
import com.robertomiranda.data.models.Comment
import com.robertomiranda.data.models.Post

class PostCommentViewHolder(var binding: RowCommentBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Comment) {
        with(binding) {
            title.text = item.name
            body.text = item.body
        }
    }
}
