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
        val articleEntities = articlesRepository.findAll()
        return ArticleListJson(articles = articleEntities.map(::presentEntity));
    }

    private data class ArticleListJson(val articles: List<ArticleViewModel>)
}
