package com.robertomiranda.app.core

import android.content.Context
import androidx.annotation.StringRes
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.robertomiranda.app.R

fun Context.showOkDialog(@StringRes titleId: Int, @StringRes messageId: Int) {
    showOkDialog(getString(titleId), getString(messageId))
}

fun Context.showOkDialog(title: String, message: String,  positiveAction: () -> Unit = {}) {
    MaterialAlertDialogBuilder(this, R.style.AlertDialogTheme)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(android.R.string.ok) { _, _ -> positiveAction.invoke() }
        .show()
}

fun Context.showPositiveNegativeDialog(
    @StringRes titleId: Int?, @StringRes messageId: Int,
    @StringRes positiveButtonId: Int, positiveAction: () -> Unit,
    @StringRes negativeButtonId: Int, negativeAction: (() -> Unit)? = null
) {
    MaterialAlertDialogBuilder(this, R.style.AlertDialogTheme)
        .apply { titleId?.let { setTitle(it) } }
        .setMessage(messageId)
        .setPositiveButton(positiveButtonId) { _, _ -> positiveAction.invoke() }
        .setNegativeButton(negativeButtonId, { _, _ -> negativeAction?.invoke() })
        .show()
}
