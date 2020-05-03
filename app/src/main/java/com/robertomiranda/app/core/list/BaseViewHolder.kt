package com.robertomiranda.app.core.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T: ListItem>(view: View):RecyclerView.ViewHolder(view) {

    abstract fun bind(item: T)

    abstract fun onUnbind()

}