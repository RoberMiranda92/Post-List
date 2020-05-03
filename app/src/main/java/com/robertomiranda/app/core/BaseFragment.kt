package com.robertomiranda.app.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.robertomiranda.app.koin.Scopes
import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {

    private lateinit var scope: Scope
    protected lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        scope = getKoin().getOrCreateScope(getScope().name, named(getScope()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = getBinding(layoutInflater)

        return with(binding) {
            setVariablesToBinding(this)
            lifecycleOwner = viewLifecycleOwner
            configureViews()
            setUpListeners()
            observeViewModelEvents()
            root
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.close()
    }

    abstract fun getScope(): Scopes
    abstract fun getBinding(layoutInflater: LayoutInflater): T
    abstract fun setVariablesToBinding(binding: T)
    abstract fun configureViews()
    abstract fun setUpListeners()
    abstract fun observeViewModelEvents()
}