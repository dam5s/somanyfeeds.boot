package com.somanyfeeds.httpgateway

import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import org.slf4j.LoggerFactory

class OkHttpGateway(val client: OkHttpClient) : HttpGateway {
    private val logger = LoggerFactory.getLogger(OkHttpGateway::class.java)

    override fun get(url: String): String {
        logger.debug("GET {}", url)

        val request = Request.Builder().url(url).build()
        val response = client.newCall(request).execute()
        val body = response.body().string()

        logger.debug("Response: {}", response)
        logger.debug("Body:\n\n{}\n\n", body)

        return body
    }
}
