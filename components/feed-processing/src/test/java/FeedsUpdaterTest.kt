import com.somanyfeeds.feeddataaccess.FeedRepository
import com.somanyfeeds.feeddataaccess.FeedType
import com.somanyfeeds.feedprocessing.ArticleUpdater
import com.somanyfeeds.feedprocessing.FeedProcessor
import com.somanyfeeds.feedprocessing.FeedsUpdater
import io.damo.kspec.Spec
import org.mockito.Mockito.*

class FeedsUpdaterTest : Spec({

    val mockFeedRepo = mock(FeedRepository::class.java)
    val mockRssProcessor = mock(FeedProcessor::class.java)
    val mockAtomProcessor = mock(FeedProcessor::class.java)
    val mockArticleUpdater = mock(ArticleUpdater::class.java)

    val feedsUpdater = FeedsUpdater(
        feedRepository = mockFeedRepo,
        feedProcessors = mapOf(
            FeedType.RSS to mockRssProcessor,
            FeedType.ATOM to mockAtomProcessor
        ),
        articleUpdater = mockArticleUpdater
    )

    test {
        val rssFeed = buildFeedEntity(
            url = "http://example.com/gplus",
            type = FeedType.RSS
        )
        val atomFeed = buildFeedEntity(
            url = "http://example.com/github",
            type = FeedType.ATOM
        )
        doReturn(listOf(rssFeed, atomFeed)).`when`(mockFeedRepo).findAll()


        feedsUpdater.run()


        verify(mockRssProcessor).process(rssFeed)
        verify(mockAtomProcessor).process(atomFeed)
    }
})
