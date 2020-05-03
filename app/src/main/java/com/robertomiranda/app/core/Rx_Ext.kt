package com.robertomiranda.app.core

import android.util.Log
import com.robertomiranda.app.BuildConfig
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

fun <T> Flowable<T>.observeOnMainThread(): Flowable<T> =
    this.observeOn(AndroidSchedulers.mainThread())

fun <T> Flowable<T>.subscribeOnNewThread(): Flowable<T> = this.subscribeOn(Schedulers.newThread())

fun <T> Flowable<T>.subscribeOnMainThread(): Flowable<T> =
    this.subscribeOn(AndroidSchedulers.mainThread())

fun <T> Flowable<T>.subscribeOnNewObserveOnMain(): Flowable<T> =
    this.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())

fun <T> Flowable<T>.doOnErrorAndNext(
    error: (thr: Throwable) -> Unit,
    next: (any: T) -> Unit
): Flowable<T> {
    return this.doOnError { error(it) }
        .doOnNext { next(it) }
}

/**
 * When both do the same thing, and there is no need of the throwable or the data
 */
fun <T> Flowable<T>.doOnErrorAndNext(func: () -> Unit): Flowable<T> {
    return this.doOnError { func() }
        .doOnNext { func() }
}

fun <T> Flowable<T>.logLifecycle(name: String = this.toString()): Flowable<T> {
    if (!BuildConfig.DEBUG) return this

    return this
        .doOnNext { Log.d("***RxLifecycle", "$name [Next]: ${it.toString()}") }
        .doOnError { Log.d("***RxLifecycle", "$name [Error]: ${it.message}") }
        .doOnTerminate { Log.d("***RxLifecycle", "$name [Terminated]") }
        .doOnSubscribe { Log.d("***RxLifecycle", "$name [Subscribed]") }
}

fun <T> Flowable<T>.logThreadLifecycle(name: String = this.toString()): Flowable<T> {
    if (!BuildConfig.DEBUG) return this

    return this
        .doOnNext {
            Log.d(
                "***RxLifecycle",
                "$name [Next]: ${Thread.currentThread().name}, ${it.toString()}"
            )
        }
        .doOnError {
            Log.d(
                "***RxLifecycle",
                "$name [Error]: ${Thread.currentThread().name}, ${it.message}"
            )
        }
        .doOnTerminate {
            Log.d(
                "***RxLifecycle",
                "$name [Terminated] ${Thread.currentThread().name}"
            )
        }
        .doOnSubscribe {
            Log.d(
                "***RxLifecycle",
                "$name [Subscribed] ${Thread.currentThread().name}"
            )
        }
}

fun <T> Flowable<T>.subscribeAndIgnore(): Disposable? {
    return this.subscribe(
        { Log.d("***Rx", "Ignored subscription next") },
        { Log.d("***Rx", "Ignored subscription error") }
    )
}

fun <T> Maybe<T>.logLifecycle(name: String = this.toString()): Maybe<T> {
    if (!BuildConfig.DEBUG) return this

    return this
        .doOnSuccess { Log.d("***RxLifecycle", "$name [Next]: ${it.toString()}") }
        .doOnError { Log.d("***RxLifecycle", "$name [Error]: ${it.message}") }
        .doOnComplete { Log.d("***RxLifecycle", "$name [Terminated]") }
        .doOnSubscribe { Log.d("***RxLifecycle", "$name [Subscribed]") }
}

fun <T> Maybe<T>.subscribeAndIgnore(): Disposable? {
    return this.subscribe(
        { Log.d("***Rx", "Ignored subscription next") },
        { Log.d("***Rx", "Ignored subscription error") }
    )
}

fun Completable.subscribeOnNewObserveOnMain(): Completable =
    this.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())

fun <T> Maybe<T>.subscribeOnNewObserveOnMain(): Maybe<T> =
    this.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())

fun Disposable.addToDisposables(disposables: CompositeDisposable?) = disposables?.add(this)
