package com.robertomiranda.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.robertomiranda.data.api.ApiComments
import com.robertomiranda.data.api.ApiPost
import com.robertomiranda.data.api.ApiUsers
import com.robertomiranda.data.api.models.CommentApi
import com.robertomiranda.data.api.models.PostApi
import com.robertomiranda.data.api.models.UserApi
import com.robertomiranda.data.models.Comment
import com.robertomiranda.data.models.Post
import com.robertomiranda.data.models.User
import io.reactivex.observers.TestObserver
import io.reactivex.subscribers.TestSubscriber
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

        val testObserver: TestSubscriber<List<Post>> = remoteRepository.getAllPost()
            .test()

        testObserver.awaitCount(1)
        testObserver.assertResult(postList)
        testObserver.dispose()
    }

    @Test
    fun getPostFromApiOK() {
        val response =
            MockWebServerResponseBuilder().httpCode200().bodyFromFile(GET_POST_BY_ID_OK)
                .build()
        val post = Gson().fromJson<PostApi>(
            Utils.getStringFromFile(GET_POST_BY_ID_OK),
            PostApi::class.java
        ).toModel()

        server.enqueue(response)

        val testObserver: TestObserver<Post> = remoteRepository.getPostById(post.id)
            .test()

        testObserver.awaitCount(1)
        testObserver.assertResult(post)
        testObserver.dispose()
    }

    @Test
    fun getAllCommentsFromApiOK() {
        val comment = object : TypeToken<List<CommentApi>>() {}.type
        val response =
            MockWebServerResponseBuilder().httpCode200().bodyFromFile(GET_ALL_COMMENTS_OK)
                .build()
        val commentList =
            Gson().fromJson<List<CommentApi>>(Utils.getStringFromFile(GET_ALL_COMMENTS_OK), comment)
                .map { it.toModel() }

        server.enqueue(response)

        val testObserver: TestSubscriber<List<Comment>> = remoteRepository.getAllComments().test()

        testObserver.awaitCount(1)
        testObserver.assertResult(commentList)
        testObserver.dispose()
    }

    @Test
    fun getAllCommentsFromPostFromApiOK() {
        val comment = object : TypeToken<List<CommentApi>>() {}.type
        val response =
            MockWebServerResponseBuilder().httpCode200().bodyFromFile(GET_ALL_COMMENTS_OK)
                .build()
        val commentList =
            Gson().fromJson<List<CommentApi>>(Utils.getStringFromFile(GET_ALL_COMMENTS_OK), comment)
                .map { it.toModel() }.filter { it.postId == 1 }

        server.enqueue(response)

        val testObserver: TestSubscriber<List<Comment>> =
            remoteRepository.getAllCommentsFromPost(1).test()

        testObserver.awaitCount(1)
        testObserver.assertResult(commentList)
        testObserver.dispose()
    }

    @Test
    fun getAllUsersFromApiOK() {
        val userListType = object : TypeToken<List<UserApi>>() {}.type
        val response =
            MockWebServerResponseBuilder().httpCode200().bodyFromFile(GET_ALL_USERS_OK)
                .build()
        val userList =
            Gson().fromJson<List<UserApi>>(Utils.getStringFromFile(GET_ALL_USERS_OK), userListType)
                .map { it.toModel() }

        server.enqueue(response)

        val testObserver: TestSubscriber<List<User>> = remoteRepository.geAllUsers()
            .test()

        testObserver.awaitCount(1)
        testObserver.assertResult(userList)
        testObserver.dispose()
    }

    companion object {
        const val GET_ALL_POST_OK = "get_all_post_ok.json"
        const val GET_POST_BY_ID_OK = "get_post_ok.json"
        const val GET_ALL_COMMENTS_OK = "get_all_comments_ok.json"
        const val GET_ALL_USERS_OK = "get_all_users_ok.json"
    }
}