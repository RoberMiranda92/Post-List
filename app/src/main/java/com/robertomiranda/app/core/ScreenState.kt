package com.robertomiranda.app.core

const val INITIAL_STATE = 1

interface ScreenState {
    fun getInitialState(): ScreenState
}

enum class PostListScreenState : ScreenState {
    INITIAL, LOADING_DATA, DATA_LOADED, ERROR, EMPTY_DATA;

    override fun getInitialState(): ScreenState = INITIAL
}

enum class PostDetailScreenState : ScreenState {
    INITIAL, LOADING_DATA, DATA_LOADED, ERROR;

    override fun getInitialState(): ScreenState = PostListScreenState.INITIAL

}