import com.somanyfeeds.articledataaccess.ArticleRepository
import com.somanyfeeds.feedprocessing.DefaultArticleUpdater
import io.damo.kspec.Spec
import org.mockito.Mockito.*

class ArticleUpdaterTest : Spec({
    test {
        val mockArticleRepo = mock(ArticleRepository::class.java)
        val articlesUpdater = DefaultArticleUpdater(mockArticleRepo, 2)
        val feed = buildFeedEntity(id = 90)


        articlesUpdater.updateArticles(listOf(
            buildArticleEntity(id = 103),
            buildArticleEntity(id = 104),
            buildArticleEntity(id = 105)
        ), feed)


        verify(mockArticleRepo).deleteByFeed(feed)
        verify(mockArticleRepo).create(buildArticleEntity(id = 103), feed)
        verify(mockArticleRepo).create(buildArticleEntity(id = 104), feed)
        verifyNoMoreInteractions(mockArticleRepo)
    }
})
