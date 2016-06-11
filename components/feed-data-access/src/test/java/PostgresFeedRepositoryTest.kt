import com.somanyfeeds.RepositorySpec
import com.somanyfeeds.feeddataaccess.Feed
import com.somanyfeeds.feeddataaccess.FeedType
import com.somanyfeeds.feeddataaccess.PostgresFeedRepository
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat

class PostgresFeedRepositoryTest : RepositorySpec({

    val repo = PostgresFeedRepository(dataSource)

    test("#findAll") {
        execSql("TRUNCATE TABLE feed CASCADE")

        execSql("""
            INSERT INTO feed (id, name, slug, url, type) VALUES
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
                url = "http://gplus.example.com/feed.rss",
                type = FeedType.RSS
            ),
            Feed(
                id = 211,
                name = "Github",
                slug = "github",
                url = "http://github.example.com/feed.atom",
                type = FeedType.ATOM
            ),
            Feed(
                id = 212,
                name = "Tumblr",
                slug = "tumblr",
                url = "http://tumb.example.com/feed.rss",
                type = FeedType.RSS
            )
        )
        assertThat(expectedFeeds, equalTo(feeds))
    }
})
