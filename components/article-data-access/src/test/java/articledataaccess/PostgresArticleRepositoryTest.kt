package articledataaccess

import com.somanyfeeds.RepositoryTest
import com.somanyfeeds.articledataaccess.Article
import com.somanyfeeds.articledataaccess.PostgresArticleRepository
import com.somanyfeeds.feeddataaccess.Feed
import com.somanyfeeds.feeddataaccess.FeedType
import org.assertj.core.api.Assertions.assertThat
import java.time.LocalDateTime

class PostgresArticleRepositoryTest : RepositoryTest({

    val repo = PostgresArticleRepository(dataSource)

    before {
        execSql("TRUNCATE TABLE feed CASCADE")

        execSql("""
          INSERT INTO feed(id, name, slug, info, type) VALUES
          (10, 'My Feed', 'my-feed', 'http://example.com/feed.rss', 'RSS'),
          (11, 'My Other Feed', 'my-other-feed', 'http://example.com/other-feed.atom', 'ATOM'),
          (12, 'My Last Feed', 'my-last-feed', 'http://example.com/last-feed.atom', 'ATOM')
        """)
    }

    test("#findAll") {
        execSql("""
          INSERT INTO article(id, feed_id, title, link, content, date) VALUES
          (100, 10, 'My First Article', 'http://example.com/blog/articles/1', 'This is a first great article...', '2010-01-02T03:04:05'),
          (101, 10, 'My Second Article', 'http://example.com/blog/articles/2', 'This is a second great article...', '2011-01-02T03:04:05'),
          (102, 11, 'My Other First Article', 'http://example.com/blog/other-articles/1', 'This is another great article...', '2012-01-02T03:04:05')
        """)


        val entities = repo.findAll()


        val expectedArticle = Article(
            id = 102,
            title = "My Other First Article",
            link = "http://example.com/blog/other-articles/1",
            content = "This is another great article...",
            date = LocalDateTime.parse("2012-01-02T03:04:05"),
            source = "my-other-feed"
        )
        assertThat(entities).hasSize(3)
        assertThat(entities.first()).isEqualTo(expectedArticle)
    }

    test("#findAllBySlugs") {
        execSql("""
          INSERT INTO article(id, feed_id, title, link, content, date) VALUES
          (100, 10, 'My First Article', 'http://example.com/blog/articles/1', 'This is a first great article...', '2010-01-02T03:04:05'),
          (101, 11, 'My Second Article', 'http://example.com/blog/articles/2', 'This is a second great article...', '2011-01-02T03:04:05'),
          (102, 12, 'My Other Article', 'http://example.com/blog/other-articles/1', 'This is another great article...', '2012-01-02T03:04:05')
        """)


        val entities = repo.findAllBySlugs(listOf("my-feed", "my-last-feed"))


        assertThat(entities).hasSize(2)
        assertThat(entities.first()).isEqualTo(Article(
            id = 102,
            title = "My Other Article",
            link = "http://example.com/blog/other-articles/1",
            content = "This is another great article...",
            date = LocalDateTime.parse("2012-01-02T03:04:05"),
            source = "my-last-feed"
        ))
    }

    test("#create") {
        val feed = buildFeedEntity(id = 10)
        val article = Article(
            title = "My Article",
            link = "http://example.com/my/article",
            content = "It's great and stuff...",
            date = LocalDateTime.parse("2010-01-02T03:04:05"),
            source = null
        )


        val created = repo.create(article, feed)

        assertThat(getCount("select count(*) from article where id = $created")).isEqualTo(1L)

        jdbcTemplate.query("select * from article where id = $created") { rs ->
            assertThat(rs.getLong("id")).isEqualTo(created)
            assertThat(rs.getLong("feed_id")).isEqualTo(10L)
            assertThat(rs.getString("title")).isEqualTo("My Article")
            assertThat(rs.getString("link")).isEqualTo("http://example.com/my/article")
            assertThat(rs.getString("content")).isEqualTo("It's great and stuff...")
            assertThat(rs.getString("date")).isEqualTo("2010-01-02 03:04:05")
        }
    }

    test("#deleteByFeed") {
        execSql("""
            INSERT INTO article(id, feed_id, title, link, content, date) VALUES
            (20, 10, 'My Article 1', 'http://example.com/article1', 'content 1...', '2010-01-02T03:04:05Z'),
            (21, 10, 'My Article 2', 'http://example.com/article2', 'content 2...', '2010-01-02T03:04:05Z'),
            (22, 11, 'My Article 3', 'http://example.com/article3', 'content 3...', '2010-01-02T03:04:05Z')
        """)


        repo.deleteByFeed(buildFeedEntity(id = 10))


        assertThat(getCount("select count(*) from article")).isEqualTo(1L)

        jdbcTemplate.query("SELECT * FROM article") { rs ->
            assertThat(rs.getLong("id")).isEqualTo(22L)
            assertThat(rs.getLong("feed_id")).isEqualTo(11L)
            assertThat(rs.getString("title")).isEqualTo("My Article 3")
            assertThat(rs.getString("link")).isEqualTo("http://example.com/article3")
            assertThat(rs.getString("content")).isEqualTo("content 3...")
            assertThat(rs.getString("date")).isEqualTo("2010-01-02 03:04:05")
        }
    }
})

private fun buildFeedEntity(
    id: Long? = 10,
    name: String = "My Feed",
    slug: String = "my-feed",
    info: String = "http://example.com/feed.rss",
    type: FeedType = FeedType.RSS
) = Feed(id, name, slug, info, type)
