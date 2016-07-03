package com.somanyfeeds

import com.somanyfeeds.articledataaccess.ArticleRepository
import com.somanyfeeds.feedprocessing.DefaultArticleUpdater
import com.somanyfeeds.httpgateway.OkHttpGateway
import com.somanyfeeds.jsonserialization.ObjectMapperProvider
import com.squareup.okhttp.OkHttpClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.social.twitter.api.Twitter
import org.springframework.social.twitter.api.impl.TwitterTemplate
import java.util.*
import java.util.concurrent.ScheduledThreadPoolExecutor

@SpringBootApplication
open class SoManyFeedsApplication {

    @Bean
    @Primary
    open fun objectMapper() = ObjectMapperProvider().get()

    @Bean
    open fun twitter(@Value("\${spring.social.twitter.appId}") appId: String,
                     @Value("\${spring.social.twitter.appSecret}") appSecret: String): Twitter {

        return TwitterTemplate(appId, appSecret)
    }

    @Bean
    open fun httpGateway() = OkHttpGateway(OkHttpClient())

    @Bean
    open fun execService() = ScheduledThreadPoolExecutor(2)

    @Bean
    open fun articleUpdater(articleRepo: ArticleRepository) = DefaultArticleUpdater(articleRepo, 20)
}

fun main(args: Array<String>) {
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
    SpringApplication.run(SoManyFeedsApplication::class.java, *args)
}
