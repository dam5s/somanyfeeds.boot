import com.somanyfeeds.somanyfeedsapplication.ArticlesController
import io.damo.kotlinext.nn
import org.hamcrest.Matchers.hasSize
import org.junit.Before
import org.junit.Test
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup
import testing.FakeArticlesRepository
import testing.buildArticleEntity
import java.time.ZonedDateTime

class ArticlesControllerTest {
    var repository: FakeArticlesRepository by nn();
    var controller: ArticlesController by nn();
    var mockMvc: MockMvc by nn();

    @Before
    fun setup() {
        repository = FakeArticlesRepository();
        controller = ArticlesController(repository);
        mockMvc = standaloneSetup(controller).build()
    }

    @Test
    fun listArticles_HappyPath() {
        repository.articles = arrayListOf(
            buildArticleEntity(
                id = 100,
                title = "Awesome Article",
                link = "http://example.com/articles/1",
                content = "This is it.",
                date = ZonedDateTime.parse("2011-02-03T04:05:06+00:00")
            ),
            buildArticleEntity(id = 101),
            buildArticleEntity(id = 102)
        )

        mockMvc
            .perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.articles", hasSize<Any>(3)))

            .andExpect(jsonPath("$.articles[0].id").value(100))
            .andExpect(jsonPath("$.articles[0].title").value("Awesome Article"))
            .andExpect(jsonPath("$.articles[0].link").value("http://example.com/articles/1"))
            .andExpect(jsonPath("$.articles[0].content").value("This is it."))

            .andExpect(jsonPath("$.articles[1].id").value(101))
            .andExpect(jsonPath("$.articles[2].id").value(102))
    }
}
