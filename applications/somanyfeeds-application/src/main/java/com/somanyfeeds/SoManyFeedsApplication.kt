package com.somanyfeeds

import com.somanyfeeds.articledataaccess.ArticleRepository
import com.somanyfeeds.articledataaccess.PostgresArticleRepository
import com.somanyfeeds.feeddataaccess.FeedRepository
import com.somanyfeeds.feeddataaccess.FeedType
import com.somanyfeeds.feeddataaccess.PostgresFeedRepository
import com.somanyfeeds.feedprocessing.ArticleUpdater
import com.somanyfeeds.feedprocessing.DefaultArticleUpdater
import com.somanyfeeds.feedprocessing.FeedsUpdater
import com.somanyfeeds.feedprocessing.atom.AtomFeedProcessor
import com.somanyfeeds.feedprocessing.rss.RssFeedProcessor
import com.somanyfeeds.httpgateway.HttpGateway
import com.somanyfeeds.httpgateway.OkHttpGateway
import com.somanyfeeds.jsonserialization.ObjectMapperProvider
import com.squareup.okhttp.OkHttpClient
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import java.util.*
import java.util.concurrent.ScheduledThreadPoolExecutor
import javax.sql.DataSource

@SpringBootApplication
open class SoManyFeedsApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
            SpringApplication.run(SoManyFeedsApplication::class.java, *args)
        }
    }

    @Bean
    @Primary
    open fun objectMapper() = ObjectMapperProvider().get()

    @Bean
    open fun httpGateway() = OkHttpGateway(OkHttpClient())

    @Bean
    open fun execService() = ScheduledThreadPoolExecutor(2)

    @Bean
    open fun articleUpdater(articleRepo: ArticleRepository) = DefaultArticleUpdater(articleRepo, 20)

    @Bean
    open fun feedRepository(dataSource: DataSource) = PostgresFeedRepository(dataSource)

    @Bean
    open fun articleRepository(dataSource: DataSource) = PostgresArticleRepository(dataSource)

    @Bean
    open fun feedsUpdater(feedRepo: FeedRepository, articleUpdater: ArticleUpdater, httpGateway: HttpGateway)
        = FeedsUpdater(feedRepo, articleUpdater, mapOf(
            FeedType.RSS to RssFeedProcessor(httpGateway),
            FeedType.ATOM to AtomFeedProcessor(httpGateway)
        ))
}
