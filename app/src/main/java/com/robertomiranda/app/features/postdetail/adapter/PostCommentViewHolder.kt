package com.robertomiranda.app.features.postdetail.adapter

import com.robertomiranda.app.core.addToDisposables
import com.robertomiranda.app.core.list.BaseViewHolder
import com.robertomiranda.app.core.ui.show
import com.robertomiranda.app.databinding.RowCommentBinding
import com.robertomiranda.app.features.postdetail.PostDetailViewModel
import com.robertomiranda.app.features.postdetail.adapter.models.CommentListItem
import io.reactivex.disposables.CompositeDisposable

class PostCommentViewHolder(
    private var binding: RowCommentBinding,
    private val viewModel: PostDetailViewModel
) :
    BaseViewHolder<CommentListItem>(binding.root) {

    private val disposables: CompositeDisposable = CompositeDisposable()

    override fun bind(item: CommentListItem) {
        with(binding) {
            email.text = item.comment.email
            title.text = item.comment.name
            body.text = item.comment.body
        }

        loadResource(item.comment.email)
    }

    private fun loadResource(email: String) {
        viewModel
            .loadCommentResource(email)
            .subscribe(
                { resource -> showResource(resource.value) },
                { error -> /*Do nothing */ }
            )
            .addToDisposables(disposables)
    }

    private fun showResource(value: String) {
        with(binding.emoji) {
            text = value
            show()
        }
    }

    override fun onUnbind() {
        disposables.dispose()
    }
}
