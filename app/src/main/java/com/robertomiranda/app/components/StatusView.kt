package com.robertomiranda.app.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import com.robertomiranda.app.R
import com.robertomiranda.app.databinding.ViewStatusBinding


class StatusView(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0, 0)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int = 0) : this(
        context,
        attrs,
        defStyleAttr,
        0
    )

    var title: CharSequence
        get() = binding.title.text
        set(value) {
            binding.title.text = value
        }

    var subTitle: CharSequence
        get() = binding.subtitle.text
        set(value) {
            binding.subtitle.text = value
        }

    private val binding: ViewStatusBinding =
        ViewStatusBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.StatusView,
            0, 0
        ).apply {
            try {
                title = (getString(R.styleable.StatusView_view_title_text) ?: "")
                subTitle = (getString(R.styleable.StatusView_view_subtitle_text) ?: "")
            } finally {
                recycle()
            }
        }
    }

}

@BindingAdapter("view_title_text")
fun StatusView.setTitle(title: String) {
    this.title = title
}

@BindingAdapter("view_subtitle_text")
fun StatusView.setSubtitle(subtitle: String) {
    this.subTitle = subTitle

}