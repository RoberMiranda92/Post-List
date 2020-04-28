package com.robertomiranda.data

import io.reactivex.schedulers.Schedulers
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.io.InputStreamReader

abstract class BaseTest {

    lateinit var server: MockWebServer

    @Before
    open fun setUp() {
        server = MockWebServer()
        server.start()

        setUpChild()
    }

    open fun setUpChild() {
        // override if needed
    }

    open fun tearDownChild() {
        // Override if needed
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        server.shutdown()
        tearDownChild()
    }

    inline fun <reified S> createApiService(): S {
        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .baseUrl(server.url("/"))
            .build()

        return retrofit.create(S::class.java)
    }
}
