package com.robertomiranda.app

import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Before
import java.util.concurrent.Executor

/**
 * - Forces Flowable streams to be immediate.
 * - We don't use Android dependency schedulers
 * - Resets on every test so there's no interference.
 */
abstract class RxBaseTest {

    @Before
    open fun setUp() {
        setupRxJavaSchedulers()
        setUpChild()
    }

    open fun setUpChild() {
        // override if needed
    }

    /**
     * https://stackoverflow.com/questions/43356314/android-rxjava-2-junit-test-getmainlooper-in-android-os-looper-not-mocked-runt
     */
    private fun setupRxJavaSchedulers() {
        val immediate = object : Scheduler() {
            override fun scheduleDirect(
                run: Runnable,
                delay: Long,
                unit: java.util.concurrent.TimeUnit
            ): Disposable {
                // this prevents StackOverflowErrors when scheduling with a delay
                return super.scheduleDirect(run, 0, unit)
            }

            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(Executor { it.run() }, false)
            }
        }
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
        RxJavaPlugins.setInitIoSchedulerHandler { immediate }
        RxJavaPlugins.setNewThreadSchedulerHandler { immediate }
        RxJavaPlugins.setInitComputationSchedulerHandler { immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediate }
    }
}