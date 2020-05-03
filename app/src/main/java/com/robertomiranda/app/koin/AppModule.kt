package com.robertomiranda.app.koin


import com.robertomiranda.app.features.postdetail.PostDetailViewModel
import com.robertomiranda.app.features.postdetail.data.PostDetailProvider
import com.robertomiranda.app.features.postlist.PostListViewModel
import com.robertomiranda.app.features.postlist.data.PostListProvider
import com.robertomiranda.app.features.welcome.WelcomeViewModel
import com.robertomiranda.app.features.welcome.data.WelcomeDataProvider
import com.robertomiranda.data.repository.local.ILocalRepository
import com.robertomiranda.data.repository.local.LocalRepository
import com.robertomiranda.data.repository.remote.IRemoteRepository
import com.robertomiranda.data.repository.remote.RemoteRepository
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

enum class Scopes {
    WELCOME_SCOPE, POST_LIST_SCOPE, POST_DETAIL_SCOPE
}

val viewModelModules = module {

    viewModel {
        WelcomeViewModel(getScope(Scopes.WELCOME_SCOPE.name).get())
    }

    viewModel {
        PostListViewModel(getScope(Scopes.POST_LIST_SCOPE.name).get())
    }

    viewModel {
        PostDetailViewModel(getScope(Scopes.POST_DETAIL_SCOPE.name).get())
    }
}
val dataProviderModule = module {

    scope(named(Scopes.WELCOME_SCOPE)) {
        scoped<WelcomeDataProvider> { WelcomeDataProvider.newInstance(get(), get()) }
    }

    scope(named(Scopes.POST_LIST_SCOPE)) {
        scoped<PostListProvider> { PostListProvider.newInstance(get(), get()) }
    }

    scope(named(Scopes.POST_DETAIL_SCOPE)) {
        scoped<PostDetailProvider> { PostDetailProvider.newInstance(get(), get()) }
    }
}

val repositoryModules = module {
    single<IRemoteRepository> { RemoteRepository.newInstance() }
    single<ILocalRepository> { LocalRepository.newInstance(androidContext()) }
}

