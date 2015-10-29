import com.somanyfeeds.articledataaccess.ArticleEntity
import com.somanyfeeds.feeddataaccess.FeedEntity
import com.somanyfeeds.feeddataaccess.FeedType
import java.time.LocalDateTime

fun buildArticleEntity(
    id: Long? = null,
    title: String = "My Article",
    link: String = "http://example.com/articles/my-article",
    content: String = "Hello World",
    date: LocalDateTime = LocalDateTime.parse("2010-01-02T03:04:05")
) = ArticleEntity(id, title, link, content, date)

fun buildFeedEntity(
    id: Long? = null,
    name: String = "My Feed",
    slug: String = "my-feed",
    url: String = "http://example.com/feed/rss",
    type: FeedType = FeedType.RSS
) = FeedEntity(id, name, slug, url, type)
