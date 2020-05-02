package com.robertomiranda.app.features.postlist

import androidx.recyclerview.widget.RecyclerView
import com.robertomiranda.app.databinding.RowPostBinding
import com.robertomiranda.data.models.Post

class PostViewHolder(var binding: RowPostBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Post) {
        with(binding) {
            title.text = item.title
            body.text = item.body
        }
    }
}
