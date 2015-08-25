package com.somanyfeeds.somanyfeedsapplication

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.inject.Inject

@RestController
class ArticlesController {

    private val articlesRepository: ArticlesRepository

    @Inject
    constructor(articlesRepository: ArticlesRepository) {
        this.articlesRepository = articlesRepository;
    }

    @RequestMapping("/")
    fun listArticles(): ArticleListJson {
        return ArticleListJson(articles = listOf(
            Article(id = 100),
            Article(id = 101),
            Article(id = 102)
        ));
    }
}

data class ArticleListJson(val articles: List<Article>)

data class Article(val id: Int?)
