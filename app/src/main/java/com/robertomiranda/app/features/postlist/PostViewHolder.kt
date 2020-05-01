package com.robertomiranda.app.features.postlist

import androidx.recyclerview.widget.RecyclerView
import com.robertomiranda.app.databinding.RowPostBinding
import com.robertomiranda.data.models.Post

class PostViewHolder(var binding: RowPostBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Post) {
        binding.title.text = "ID: ${item.id} \n ${item.title}"
    }
}
