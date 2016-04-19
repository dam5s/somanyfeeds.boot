package com.somanyfeeds.articledataaccess

import com.somanyfeeds.feeddataaccess.FeedEntity
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import javax.sql.DataSource

class PostgresArticleRepository : ArticleRepository {

    private val jdbcTemplate: JdbcTemplate
    private val namedParamJdbcTemplate: NamedParameterJdbcTemplate

    constructor(dataSource: DataSource) {
        this.jdbcTemplate = JdbcTemplate(dataSource)
        this.namedParamJdbcTemplate = NamedParameterJdbcTemplate(dataSource)
    }


    override fun findAll() = jdbcTemplate.query("SELECT id, title, link, content, date FROM article", articleMapper)

    override fun findAllBySlugs(slugs: List<String>): List<ArticleEntity> {
        val sql = """
            SELECT a.id, a.title, a.link, a.content, a.date FROM article a
            JOIN feed f ON f.id = a.feed_id AND f.slug IN (:slugs)
        """

        val params = mapOf("slugs" to slugs)

        return namedParamJdbcTemplate.query(sql, params, articleMapper)
    }

    override fun create(article: ArticleEntity, feed: FeedEntity): Long {
        val sql = """
            INSERT INTO article (feed_id, title, link, content, date)
            VALUES (?, ?, ?, ?, ?)
            RETURNING id
        """

        return jdbcTemplate.queryForObject(sql, javaLong,
            feed.id, article.title, article.link, article.content, article.date.toDate()) as Long
    }

    override fun deleteByFeed(feed: FeedEntity) {
        jdbcTemplate.update("DELETE FROM article WHERE feed_id = ?", feed.id)
    }


    @Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")
    private val javaLong = java.lang.Long::class.java

    private val articleMapper = RowMapper { rs, rowNum ->
        ArticleEntity(
            id = rs.getLong(1),
            title = rs.getString(2),
            link = rs.getString(3),
            content = rs.getString(4),
            date = rs.getLocalDateTime(5)
        )
    }
}
