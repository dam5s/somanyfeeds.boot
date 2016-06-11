package com.somanyfeeds.articleapi

import com.somanyfeeds.articledataaccess.Article
import com.somanyfeeds.articledataaccess.ArticleRepository
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.inject.Inject

@RestController
@RequestMapping("/articles")
@CrossOrigin
class ArticlesController {

    private val articleRepository: ArticleRepository

    @Inject
    constructor(articleRepository: ArticleRepository) {
        this.articleRepository = articleRepository
    }

    @RequestMapping
    fun listAll()
        = ArticleListView(articleRepository.findAll())

    @RequestMapping("/{slugs}")
    fun listArticles(@PathVariable slugs: List<String>)
        = ArticleListView(articleRepository.findAllBySlugs(slugs))

    data class ArticleListView(val articles: Iterable<Article>)
}
