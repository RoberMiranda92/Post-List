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

    private val binding: ViewStatusBinding =
        ViewStatusBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.StatusView,
            0, 0
        ).apply {
            try {
                setTitleText(getString(R.styleable.StatusView_view_title_text) ?: "")
                setSubtitleText(getString(R.styleable.StatusView_view_subtitle_text) ?: "")
            } finally {
                recycle()
            }
        }
    }

    fun setTitleText(title: String) {
        binding.title.text = title
    }

    fun setSubtitleText(subtitle: String) {
        binding.subtitle.text = subtitle
    }

}

@BindingAdapter("view_title_text")
fun StatusView.setTitle(title: String) {
    setTitleText(title)
}

@BindingAdapter("view_subtitle_text")
fun StatusView.setSubtitle(subtitle: String) {
    setSubtitleText(subtitle)

}