import com.somanyfeeds.RepositoryTest
import com.somanyfeeds.feeddataaccess.Feed
import com.somanyfeeds.feeddataaccess.FeedType
import com.somanyfeeds.feeddataaccess.PostgresFeedRepository
import org.assertj.core.api.Assertions.assertThat


class PostgresFeedRepositoryTest : RepositoryTest({

    val repo = PostgresFeedRepository(dataSource)

    test("#findAll") {
        execSql("TRUNCATE TABLE feed CASCADE")

        execSql("""
            INSERT INTO feed (id, name, slug, info, type) VALUES
            (210, 'G+', 'g-plus', 'http://gplus.example.com/feed.rss', 'RSS'),
            (211, 'Github', 'github', 'http://github.example.com/feed.atom', 'ATOM'),
            (212, 'Tumblr', 'tumblr', 'http://tumb.example.com/feed.rss', 'RSS')
        """)


        val feeds = repo.findAll()


        val expectedFeeds = listOf(
            Feed(
                id = 210,
                name = "G+",
                slug = "g-plus",
                info = "http://gplus.example.com/feed.rss",
                type = FeedType.RSS
            ),
            Feed(
                id = 211,
                name = "Github",
                slug = "github",
                info = "http://github.example.com/feed.atom",
                type = FeedType.ATOM
            ),
            Feed(
                id = 212,
                name = "Tumblr",
                slug = "tumblr",
                info = "http://tumb.example.com/feed.rss",
                type = FeedType.RSS
            )
        )
        assertThat(expectedFeeds).isEqualTo(feeds)
    }
})
