package com.somanyfeeds.articleapi

import com.somanyfeeds.articledataaccess.Article
import com.somanyfeeds.articledataaccess.ArticleRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/articles")
@CrossOrigin
class ArticlesController(val articleRepository: ArticleRepository) {


    @GetMapping
    fun listAll()
        = ArticleListView(articleRepository.findAll())

    @GetMapping("/{slugs}")
    fun listArticles(@PathVariable slugs: List<String>)
        = ArticleListView(articleRepository.findAllBySlugs(slugs))

    data class ArticleListView(val articles: Iterable<Article>)
}
