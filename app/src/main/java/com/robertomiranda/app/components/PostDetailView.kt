package com.robertomiranda.app.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.robertomiranda.app.databinding.ViewPostDetailBinding
import com.robertomiranda.data.models.Post
import com.robertomiranda.data.models.User

class PostDetailView(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)

    private val binding: ViewPostDetailBinding =
        ViewPostDetailBinding.inflate(LayoutInflater.from(context), this, true)

    var post: Post? = null
        set(value) {
            binding.title.text = value?.title
            binding.body.text = value?.body
            field = value
        }
    var user: User? = null
        set(value) {
            binding.userrow.user = value
            field = value
        }
}