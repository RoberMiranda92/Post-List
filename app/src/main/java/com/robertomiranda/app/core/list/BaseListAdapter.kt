package com.robertomiranda.app.core.list

import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class BaseListAdapter<T : ListItem>(callback: DiffUtil.ItemCallback<T>) :
    ListAdapter<T, BaseViewHolder<T>>(
        callback
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        return getViewHolder(parent, viewType)
    }

    abstract fun getViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T>

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        val item: T? = getItem(position)

        item?.let {
            holder.bind(it)
        }
    }

    override fun onViewRecycled(holder: BaseViewHolder<T>) {
        holder.onUnbind()
    }

    override fun getItemViewType(position: Int): Int {
        return this.currentList[position].getType()
    }

    companion object {

        @JvmStatic
        @BindingAdapter("items")
        fun <T : ListItem> RecyclerView.bindItems(comments: List<T>?) {
            comments?.run {
                val adapter = adapter as BaseListAdapter<T>
                adapter.submitList(this)
            }
        }
    }
}