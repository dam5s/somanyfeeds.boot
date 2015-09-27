package articledataaccess

import com.somanyfeeds.articledataaccess.ArticleEntity
import com.somanyfeeds.articledataaccess.JpaArticleRepository
import com.somanyfeeds.dbtesting.RepositoryTest
import org.junit.Test
import java.time.ZonedDateTime
import kotlin.test.assertEquals


class ArticleRepositoryTest : RepositoryTest() {

    val repo = JpaArticleRepository(jpaRepoFactory)

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
          (100, 10, 'My First Article', 'http://example.com/blog/articles/1', 'This is a first great article...', '2010-01-02T03:04:05+0600'),
          (101, 10, 'My Second Article', 'http://example.com/blog/articles/2', 'This is a second great article...', '2011-01-02T03:04:05+0600'),
          (102, 11, 'My Other First Article', 'http://example.com/blog/other-articles/1', 'This is another great article...', '2012-01-02T03:04:05+0600')
        """)


        val entities = repo.findAll()

        val expectedArticle = ArticleEntity(
            id = 100,
            title = "My First Article",
            link = "http://example.com/blog/articles/1",
            content = "This is a first great article...",
            date = ZonedDateTime.parse("2010-01-02T03:04:05+06:00")
        )
        assertEquals(3, entities.count())
        assertEquals(expectedArticle, entities.first())
    }
}
