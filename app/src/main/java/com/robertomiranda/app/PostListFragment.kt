package com.robertomiranda.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.robertomiranda.app.core.ViewModelFactory
import com.robertomiranda.app.databinding.FragmentSecondBinding
import com.robertomiranda.app.features.postlist.PostListAdapter
import com.robertomiranda.app.features.postlist.PostListViewModel

class PostListFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding
    private val viewModel: PostListViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }
    private val adapter: PostListAdapter by lazy { PostListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater)

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
        binding.list.adapter = adapter
    }

    private fun observeViewModelEvents() {

    }
}
