package com.robertomiranda.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.robertomiranda.data.api.ApiComments
import com.robertomiranda.data.api.ApiPost
import com.robertomiranda.data.api.ApiUsers
import com.robertomiranda.data.api.models.PostApi
import com.robertomiranda.data.models.Post
import io.reactivex.observers.TestObserver
import org.junit.Test

class RemoteRepositoryTest : BaseTest() {

    private lateinit var remoteRepository: RemoteRepository
    private lateinit var apiPost: ApiPost
    private lateinit var apiComments: ApiComments
    private lateinit var apiUsers: ApiUsers

    override fun setUpChild() {
        apiPost = createApiService<ApiPost>()
        apiComments = createApiService<ApiComments>()
        apiUsers = createApiService<ApiUsers>()

        remoteRepository = RemoteRepository(apiPost, apiUsers, apiComments)
    }

    @Test
    fun getAllPostFromApiOK() {
        val postListType = object : TypeToken<List<PostApi>>() {}.type
        val response =
            MockWebServerResponseBuilder().httpCode200().bodyFromFile(GET_ALL_POST_OK)
                .build()
        val postList =
            Gson().fromJson<List<PostApi>>(Utils.getStringFromFile(GET_ALL_POST_OK), postListType)
                .map { it.toModel() }

        server.enqueue(response)

        val testObserver: TestObserver<List<Post>> = remoteRepository.getAllPost()
            .test()

        testObserver.awaitCount(1)
        testObserver.assertResult(postList)
        testObserver.dispose()
    }

    companion object {
        const val GET_ALL_POST_OK = "get_all_post_ok.json"
    }
}