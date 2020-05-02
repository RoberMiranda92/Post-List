package com.robertomiranda.app.core

import android.view.View


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