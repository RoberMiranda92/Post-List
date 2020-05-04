package com.robertomiranda.app.features.postdetail

import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayout
import com.robertomiranda.app.R
import com.robertomiranda.app.core.BaseFragment
import com.robertomiranda.app.core.PostDetailScreenState
import com.robertomiranda.app.core.showOkDialog
import com.robertomiranda.app.core.ui.hide
import com.robertomiranda.app.core.ui.show
import com.robertomiranda.app.databinding.FragmentPostDetailBinding
import com.robertomiranda.app.features.postdetail.adapter.PostCommentListAdapter
import com.robertomiranda.app.koin.Scopes
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostDetailFragment : BaseFragment<FragmentPostDetailBinding>() {

    private val viewModel: PostDetailViewModel by viewModel()
    private val args: PostDetailFragmentArgs by navArgs()
    private val adapter: PostCommentListAdapter by lazy { PostCommentListAdapter(viewModel) }

    override fun getScope(): Scopes = Scopes.POST_DETAIL_SCOPE

    override fun getBinding(layoutInflater: LayoutInflater): FragmentPostDetailBinding {
        return FragmentPostDetailBinding.inflate(layoutInflater)
    }

    override fun setVariablesToBinding(binding: FragmentPostDetailBinding) {
        with(binding) {
            vm = viewModel
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState == null) {
            viewModel.loadPostDetails(args.postId)
        }
    }

    override fun setUpListeners() {
        binding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                /* Do nothing */
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                /* Do nothing */
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    DESCRIPTION_POSITION -> {
                        showDetail()
                    }
                    COMMENTS_POSITION -> {
                        showComments()
                    }
                    else -> {
                        error("Invalid view")
                    }
                }
            }
        })

        binding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
    }

    override fun configureViews() {
        with(binding) {
            toolbar.title = findNavController().currentDestination?.label
            toolbar.setNavigationIcon(R.drawable.ic_arrow)
            list.adapter = adapter
        }
    }

    override fun observeViewModelEvents() {
        viewModel.screenState.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                PostDetailScreenState.ERROR -> {
                    configureErrorView()
                }
                else -> {
                    /* Do nothing */
                }
            }
        })

        viewModel.postData.observe(viewLifecycleOwner, Observer { postDetail ->
            binding.detail.post = postDetail.post
            binding.detail.user = postDetail.user
        })

        viewModel.commentError.observe(viewLifecycleOwner, Observer { error ->
            context?.showOkDialog(
                R.string.post_detail_comments_error_title,
                R.string.post_detail_comments_error_subtitle
            )
        })
    }

    private fun showDetail() {
        binding.detail.show()
        binding.list.hide()
    }

    private fun showComments() {
        binding.list.show()
        binding.detail.hide()
    }

    private fun configureErrorView() {
        with(binding.status) {
            title = getString(R.string.post_list_error_title)
            subTitle = getString(R.string.post_list_error_subtitle)
        }
    }

    companion object {
        const val DESCRIPTION_POSITION = 0
        const val COMMENTS_POSITION = 1
    }
}
