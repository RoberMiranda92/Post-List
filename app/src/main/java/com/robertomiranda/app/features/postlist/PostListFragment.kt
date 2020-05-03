package com.robertomiranda.app.features.postlist

import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.robertomiranda.app.R
import com.robertomiranda.app.core.BaseFragment
import com.robertomiranda.app.core.PostListScreenState
import com.robertomiranda.app.databinding.FragmentPostListBinding
import com.robertomiranda.app.features.postlist.PostListFragmentDirections.actionPostListFragmentToPostDetailFragment
import com.robertomiranda.app.features.postlist.adapter.PostListAdapter
import com.robertomiranda.app.koin.Scopes
import com.robertomiranda.data.models.Post
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostListFragment : BaseFragment<FragmentPostListBinding>() {

    private val viewModel: PostListViewModel by viewModel()
    private val adapter: PostListAdapter by lazy { PostListAdapter { post -> managePostClick(post) } }

    override fun getScope(): Scopes = Scopes.POST_LIST_SCOPE

    override fun getBinding(layoutInflater: LayoutInflater): FragmentPostListBinding {
        return FragmentPostListBinding.inflate(layoutInflater)
    }

    override fun setVariablesToBinding(binding: FragmentPostListBinding) {
        with(binding) {
            vm = viewModel
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.loadPostList()
    }

    override fun setUpListeners() {

    }

    override fun configureViews() {
        with(binding) {
            toolbar.title = findNavController().currentDestination?.label
            list.adapter = adapter
        }
    }

    override fun observeViewModelEvents() {
        viewModel.screenState.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                PostListScreenState.ERROR -> {
                    configureErrorView()
                }

                PostListScreenState.EMPTY_DATA -> {
                    configureEmptyView()
                }

                else -> {
                    /* Do nothing */
                }
            }
        })
    }

    private fun configureEmptyView() {
        with(binding.status) {
            title = getString(R.string.post_list_empty_title)
            subTitle = getString(R.string.post_list_empty_subtitle)
        }
    }

    private fun configureErrorView() {
        with(binding.status) {
            title = getString(R.string.post_list_error_title)
            subTitle = getString(R.string.post_list_error_subtitle)
        }
    }

    private fun managePostClick(post: Post) {
        findNavController().navigate(actionPostListFragmentToPostDetailFragment().setPostId(post.id))
    }
}
