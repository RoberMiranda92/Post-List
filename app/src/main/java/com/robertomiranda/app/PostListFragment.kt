package com.robertomiranda.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.robertomiranda.app.core.PostListScreenState
import com.robertomiranda.app.core.ViewModelFactory
import com.robertomiranda.app.databinding.FragmentPostListBinding
import com.robertomiranda.app.features.postlist.PostListAdapter
import com.robertomiranda.app.features.postlist.PostListViewModel

class PostListFragment : Fragment() {

    private lateinit var binding: FragmentPostListBinding
    private val viewModel: PostListViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }
    private val adapter: PostListAdapter by lazy { PostListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostListBinding.inflate(inflater)

        return with(binding) {
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner
            configureViews()
            setUpListeners()
            observeViewModelEvents()
            root
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.loadPostList()
    }

    private fun setUpListeners() {

    }

    private fun configureViews() {
        with(binding) {
            toolbar.title = findNavController().currentDestination?.label
            list.adapter = adapter
        }
    }

    private fun observeViewModelEvents() {
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
}
