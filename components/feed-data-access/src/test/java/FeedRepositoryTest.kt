import com.somanyfeeds.RepositoryTest
import com.somanyfeeds.feeddataaccess.FeedEntity
import com.somanyfeeds.feeddataaccess.FeedType
import com.somanyfeeds.feeddataaccess.JpaFeedRepository
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test
import javax.inject.Inject

class FeedRepositoryTest : RepositoryTest() {

    @Inject
    lateinit var repo: JpaFeedRepository

    @Test
    fun testFindAll() {
        execSql("truncate table feed cascade")

        execSql("""
            insert into feed (id, name, slug, url, type) values
            (210, 'G+', 'g-plus', 'http://gplus.example.com/feed.rss', 'RSS'),
            (211, 'Github', 'github', 'http://github.example.com/feed.atom', 'ATOM'),
            (212, 'Tumblr', 'tumblr', 'http://tumb.example.com/feed.rss', 'RSS');
        """)


        val feeds = repo.findAll()


        val expectedFeeds = listOf(
            FeedEntity(
                id = 210,
                name = "G+",
                slug = "g-plus",
                url = "http://gplus.example.com/feed.rss",
                type = FeedType.RSS
            ),
            FeedEntity(
                id = 211,
                name = "Github",
                slug = "github",
                url = "http://github.example.com/feed.atom",
                type = FeedType.ATOM
            ),
            FeedEntity(
                id = 212,
                name = "Tumblr",
                slug = "tumblr",
                url = "http://tumb.example.com/feed.rss",
                type = FeedType.RSS
            )
        )
        assertThat(expectedFeeds, equalTo(feeds))
    }
}
