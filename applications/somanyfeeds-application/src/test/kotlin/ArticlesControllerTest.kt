import TestArticlesRepository
import com.somanyfeeds.somanyfeedsapplication.ArticlesController
import com.somanyfeeds.somanyfeedsapplication.ArticlesRepository
import io.damo.hamcrestkotlin.hasSize
import io.damo.springtestkotlin.get
import io.damo.springtestkotlin.jsonPath
import io.damo.springtestkotlin.standaloneSetup
import io.damo.springtestkotlin.status
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Test
import org.springframework.test.web.servlet.MockMvc

class ArticlesControllerTest {
    var repository: TestArticlesRepository? = null;
    var controller: ArticlesController? = null;
    var mockMvc: MockMvc? = null;

    @Before
    fun setup() {
        repository = TestArticlesRepository();
        controller = ArticlesController(repository!!);
        mockMvc = standaloneSetup(controller!!).build()
    }

    @Test
    fun listArticles_HappyPath() {
        mockMvc!!
            .perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.articles", hasSize<Any>(3)))
    }
}

class TestArticlesRepository: ArticlesRepository {

}
