package com.robertomiranda.app.features.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.robertomiranda.app.R
import com.robertomiranda.app.core.BaseFragment
import com.robertomiranda.app.core.WelcomeScreenState
import com.robertomiranda.app.core.showOkDialog
import com.robertomiranda.app.databinding.FragmentWelcomeBinding
import com.robertomiranda.app.features.welcome.WelcomeFragmentDirections.actionWelcomeFragmentToPostListFragment
import com.robertomiranda.app.koin.Scopes
import org.koin.androidx.viewmodel.ext.android.viewModel

class WelcomeFragment : BaseFragment<FragmentWelcomeBinding>() {

    private val viewModel: WelcomeViewModel by viewModel()

    override fun getScope(): Scopes = Scopes.WELCOME_SCOPE

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            viewModel.loadDataFromRemote()
        }
    }

    override fun getBinding(layoutInflater: LayoutInflater): FragmentWelcomeBinding {
        return FragmentWelcomeBinding.inflate(layoutInflater)
    }

    override fun setVariablesToBinding(binding: FragmentWelcomeBinding) {
        with(binding) {
            vm = viewModel
        }
    }

    override fun configureViews() {
    }

    override fun setUpListeners() {
    }

    override fun observeViewModelEvents() {
        viewModel.screenState.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                WelcomeScreenState.DATA_LOADED -> {
                    navigateToPostList()
                }
                WelcomeScreenState.ERROR -> {
                    showLoadError()
                }
                else -> {
                    /*Do Nothing*/
                }
            }
        })
    }

    private fun showLoadError() {
        context?.showOkDialog(
            getString(R.string.welcome_error_title),
            getString(R.string.welcome_error_subtitle)
        ) { navigateToPostList() }
    }

    private fun navigateToPostList() {
        with(findNavController()) {
            navigate(actionWelcomeFragmentToPostListFragment())
        }
    }
}
