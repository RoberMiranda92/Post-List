package com.robertomiranda.app.core.ui

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager

// FIX for diffutils error on api 22
//FIX https://stackoverflow.com/questions/46563485/diffresult-dispatching-lead-to-inconsistency-detected-invalid-view-holder-adap
class LinearLayoutManagerWrapper : LinearLayoutManager {
    constructor(context: Context?) : super(context) {}

    constructor(context: Context?, orientation: Int, reverseLayout: Boolean) : super(
        context, orientation, reverseLayout) {
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
    }

    override fun supportsPredictiveItemAnimations(): Boolean {
        return false
    }
}