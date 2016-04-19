import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.somanyfeeds.feeddataaccess.FeedRepository
import com.somanyfeeds.feeddataaccess.FeedType
import com.somanyfeeds.feedprocessing.ArticleUpdater
import com.somanyfeeds.feedprocessing.FeedProcessor
import com.somanyfeeds.feedprocessing.FeedsUpdater
import io.damo.kspec.Spec

class FeedsUpdaterTest : Spec({

    val mockFeedRepo: FeedRepository = mock()
    val mockRssProcessor: FeedProcessor = mock()
    val mockAtomProcessor: FeedProcessor = mock()
    val mockArticleUpdater: ArticleUpdater = mock()

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
        doReturn(listOf(rssFeed, atomFeed)).whenever(mockFeedRepo).findAll()


        feedsUpdater.run()


        verify(mockRssProcessor).process(rssFeed)
        verify(mockAtomProcessor).process(atomFeed)
    }
})
