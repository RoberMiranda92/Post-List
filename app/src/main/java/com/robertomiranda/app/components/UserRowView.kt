package com.robertomiranda.app.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.robertomiranda.app.core.ui.loadImageFromUrl
import com.robertomiranda.app.databinding.RowUserBinding
import com.robertomiranda.data.getAvatarUrl
import com.robertomiranda.data.models.User

class UserRowView(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)

    private val binding: RowUserBinding =
        RowUserBinding.inflate(LayoutInflater.from(context), this, true)

    var user: User? = null
        set(value) {
            binding.username.text = value?.username
            binding.email.text = value?.email
            binding.image.loadImageFromUrl(value?.getAvatarUrl())
            field = value
        }
}