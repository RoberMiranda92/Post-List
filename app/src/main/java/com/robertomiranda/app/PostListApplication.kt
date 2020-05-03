package com.robertomiranda.app

import android.app.Application
import com.robertomiranda.app.koin.dataProviderModule
import com.robertomiranda.app.koin.repositoryModules
import com.robertomiranda.app.koin.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PostListApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        configureKoin()
    }

    private fun configureKoin() {
        startKoin {  // Koin Android logger
            androidLogger()
            //inject Android context
            androidContext(this@PostListApplication)
            // use modules
            modules(repositoryModules, dataProviderModule, viewModelModules)
        }
    }
}