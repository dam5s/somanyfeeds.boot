package articledataaccess

import com.somanyfeeds.RepositoryTest
import com.somanyfeeds.articledataaccess.ArticleEntity
import com.somanyfeeds.articledataaccess.JpaArticleRepository
import com.somanyfeeds.feeddataaccess.FeedEntity
import com.somanyfeeds.feeddataaccess.FeedType
import org.junit.Test
import java.time.LocalDateTime
import javax.inject.Inject
import kotlin.test.assertEquals


class ArticleRepositoryTest : RepositoryTest() {

    @Inject
    lateinit var repo: JpaArticleRepository

    @Test
    fun testFindAll() {
        execSql("truncate table feed cascade")

        execSql("""
          insert into feed(id, name, slug, url, type) values
          (10, 'My Feed', 'my-feed', 'http://example.com/feed.rss', 'RSS'),
          (11, 'My Other Feed', 'my-other-feed', 'http://example.com/other-feed.atom', 'ATOM')
        """)

        execSql("""
          insert into article(id, feed_id, title, link, content, date) values
          (100, 10, 'My First Article', 'http://example.com/blog/articles/1', 'This is a first great article...', '2010-01-02T03:04:05'),
          (101, 10, 'My Second Article', 'http://example.com/blog/articles/2', 'This is a second great article...', '2011-01-02T03:04:05'),
          (102, 11, 'My Other First Article', 'http://example.com/blog/other-articles/1', 'This is another great article...', '2012-01-02T03:04:05')
        """)


        val entities = repo.findAll()


        val expectedArticle = ArticleEntity(
            id = 100,
            title = "My First Article",
            link = "http://example.com/blog/articles/1",
            content = "This is a first great article...",
            date = LocalDateTime.parse("2010-01-02T03:04:05")
        )
        assertEquals(3, entities.count())
        assertEquals(expectedArticle, entities.first())
    }

    @Test
    fun testCreate() {
        execSql("truncate table feed cascade")
        execSql("""
            insert into feed(id, name, slug, url, type) values
            (10, 'My Feed', 'my-feed', 'http://example.com/feed.rss', 'RSS')
        """)


        val article = ArticleEntity(
            title = "My Article",
            link = "http://example.com/my/article",
            content = "It's great and stuff...",
            date = LocalDateTime.parse("2010-01-02T03:04:05")
        )
        val feed = FeedEntity(
            id = 10,
            name = "My Feed",
            slug = "my-feed",
            url = "http://example.com/feed.rss",
            type = FeedType.RSS
        )


        val created = repo.create(article, feed)


        var found = 0
        jdbcTemplate.query("select * from article where id = $created") { rs ->
            found++

            assertEquals(created, rs.getLong("id"))
            assertEquals(10L, rs.getLong("feed_id"))
            assertEquals("My Article", rs.getString("title"))
            assertEquals("http://example.com/my/article", rs.getString("link"))
            assertEquals("It's great and stuff...", rs.getString("content"))
            assertEquals("2010-01-02 03:04:05", rs.getString("date"))
        }

        assertEquals(1, found)
    }

    @Test
    fun testDeleteByFeed() {
        execSql("truncate table feed cascade")
        execSql("""
            insert into feed(id, name, slug, url, type) values
            (10, 'My Feed', 'my-feed', 'http://example.com/feed.rss', 'RSS'),
            (11, 'My Other Feed', 'my-other-feed', 'http://example.com/other-feed.atom', 'ATOM')
        """)
        execSql("""
            insert into article(id, feed_id, title, link, content, date) values
            (20, 10, 'My Article 1', 'http://example.com/article1', 'content 1...', '2010-01-02T03:04:05Z'),
            (21, 10, 'My Article 2', 'http://example.com/article2', 'content 2...', '2010-01-02T03:04:05Z'),
            (22, 11, 'My Article 3', 'http://example.com/article3', 'content 3...', '2010-01-02T03:04:05Z')
        """)


        repo.deleteByFeed(buildFeedEntity(id = 10))


        var found = 0
        jdbcTemplate.query("select * from article") { rs ->
            found++

            assertEquals(22L, rs.getLong("id"))
            assertEquals(11L, rs.getLong("feed_id"))
            assertEquals("My Article 3", rs.getString("title"))
            assertEquals("http://example.com/article3", rs.getString("link"))
            assertEquals("content 3...", rs.getString("content"))
            assertEquals("2010-01-02 03:04:05", rs.getString("date"))
        }
        assertEquals(1, found)
    }
}

private fun buildFeedEntity(
    id: Long? = 10,
    name: String = "My Feed",
    slug: String = "my-feed",
    url: String = "http://example.com/feed.rss",
    type: FeedType = FeedType.RSS
) = FeedEntity(id, name, slug, url, type)
