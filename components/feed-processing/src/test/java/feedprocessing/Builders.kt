package feedprocessing

import com.somanyfeeds.articledataaccess.Article
import com.somanyfeeds.feeddataaccess.Feed
import com.somanyfeeds.feeddataaccess.FeedType
import java.time.LocalDateTime

fun buildArticle(
    id: Long? = null,
    title: String = "My Article",
    link: String = "http://example.com/articles/my-article",
    content: String = "Hello World",
    date: LocalDateTime = LocalDateTime.parse("2010-01-02T03:04:05"),
    source: String = "Blog"
) = Article(id, title, link, content, date, source)

fun buildFeed(
    id: Long? = null,
    name: String = "My Feed",
    slug: String = "my-feed",
    url: String = "http://example.com/feed/rss",
    type: FeedType = FeedType.RSS
) = Feed(id, name, slug, url, type)
