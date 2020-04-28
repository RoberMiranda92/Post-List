package com.robertomiranda.app.utils

import androidx.paging.*

//Example from https://stackoverflow.com/questions/50435770/how-do-i-create-a-pagedlist-of-an-object-for-tests

fun <T> List<T>.asPagedList(): PagedList<T> = LivePagedListBuilder<Int, T>(
    createMockDataSourceFactory(this),
    Config(
        pageSize = if (size == 0) 1 else size,
        enablePlaceholders = false,
        prefetchDistance = 24
    )
).build().getOrAwaitValue()

private fun <T> createMockDataSourceFactory(itemList: List<T>): DataSource.Factory<Int, T> =
    object : DataSource.Factory<Int, T>() {
        override fun create(): DataSource<Int, T> = MockLimitDataSource(itemList)
    }

/**
 * See [ListDataSource]
 */
class MockLimitDataSource<T>(private val itemList: List<T>) : PositionalDataSource<T>() {

    override fun isInvalid(): Boolean = false

    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<T>
    ) {
        val totalCount: Int = itemList.size
        val position = computeInitialLoadPosition(params, totalCount)
        val loadSize =
            computeInitialLoadSize(params, position, totalCount)

        // for simplicity, we could return everything immediately,
        // but we tile here since it's expected behavior
        val sublist: List<T> = itemList.subList(position, position + loadSize)
        callback.onResult(sublist, position, totalCount)
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<T>) {
        callback.onResult(
            itemList.subList(
                params.startPosition,
                params.startPosition + params.loadSize
            )
        )
    }
}
