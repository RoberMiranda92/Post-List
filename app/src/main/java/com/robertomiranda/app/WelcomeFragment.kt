package com.robertomiranda.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.robertomiranda.app.WelcomeFragmentDirections.actionWelcomeFragmentToPostListFragment
import com.robertomiranda.app.core.ViewModelFactory
import com.robertomiranda.app.core.WelcomeScreenState
import com.robertomiranda.app.core.showOkDialog
import com.robertomiranda.app.databinding.FragmentWelcomeBinding
import com.robertomiranda.app.features.welcome.WelcomeViewModel

class WelcomeFragment : Fragment() {

    private val viewModel: WelcomeViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }
    private lateinit var binding: FragmentWelcomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWelcomeBinding.inflate(layoutInflater)

        return with(binding) {
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner
            observeViewModelEvents()
            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadDataFromRemote()
    }

    private fun observeViewModelEvents() {
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
