package com.somanyfeeds.httpgateway

interface HttpGateway {
    fun get(url: String): String
}
