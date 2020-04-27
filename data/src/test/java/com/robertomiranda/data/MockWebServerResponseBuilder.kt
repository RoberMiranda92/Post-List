package com.robertomiranda.data

import okhttp3.mockwebserver.MockResponse
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection

class MockWebServerResponseBuilder {
    private val mockResponse: MockResponse

    constructor() {
        mockResponse = MockResponse()
    }

    constructor(code: Int) {
        mockResponse = MockResponse()
        mockResponse.setResponseCode(code)
    }

    fun httpCode200(): MockWebServerResponseBuilder =
        apply {
            mockResponse.setResponseCode(HttpURLConnection.HTTP_OK)
        }

    fun httpCode404(): MockWebServerResponseBuilder =
        apply {
            mockResponse.setResponseCode(HttpURLConnection.HTTP_NOT_FOUND)
        }

    fun httpCode500(): MockWebServerResponseBuilder =
        apply {
            mockResponse.setResponseCode(HttpURLConnection.HTTP_INTERNAL_ERROR)
        }

    fun body(body: String): MockWebServerResponseBuilder =
        apply {
            mockResponse.setBody(body)
        }

    fun emptyBody(): MockWebServerResponseBuilder =
        apply {
            mockResponse.setBody("{}")
        }

    fun bodyFromFile(path: String): MockWebServerResponseBuilder =
        apply {
            val body = Utils.getStringFromFile(path)
            mockResponse.setBody(body)
        }

    fun build(): MockResponse {
        return mockResponse
    }
}
