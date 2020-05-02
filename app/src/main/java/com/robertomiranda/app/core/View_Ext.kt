package com.robertomiranda.app.core

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import com.robertomiranda.app.GlideApp

fun View?.showOrHide(boolean: Boolean) {
    this?.visibility = if (boolean) View.VISIBLE else View.GONE
}

fun View?.showOrInvisible(boolean: Boolean) {
    this?.visibility = if (boolean) View.VISIBLE else View.INVISIBLE
}

fun View?.show() {
    this.showOrHide(true)
}

fun View?.hide() {
    this?.showOrHide(false)
}

fun AppCompatImageView.loadImageFromUrl(url: String?) {
    context?.let {
        GlideApp.with(it).load(url).into(this)
    }
}