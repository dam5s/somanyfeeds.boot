package com.somanyfeeds

import io.damo.aspen.Test
import io.damo.aspen.spring.SpringTestTreeRunner
import io.damo.aspen.spring.inject
import org.junit.runner.RunWith
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.jdbc.core.JdbcTemplate
import java.util.*
import javax.sql.DataSource


@RunWith(SpringTestTreeRunner::class)
@SpringApplicationConfiguration(classes = arrayOf(RepositoryTestConfiguration::class))
open class RepositorySpec : Test {

    lateinit var dataSource: DataSource
    lateinit var jdbcTemplate: JdbcTemplate


    private val body: RepositorySpec.() -> Unit

    constructor(body: RepositorySpec.() -> Unit) : super({}) {
        this.body = body
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
    }


    fun execSql(sql: String) = jdbcTemplate.execute(sql)

    @Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")
    fun getCount(sql: String) = jdbcTemplate.queryForObject(sql, java.lang.Long::class.java) as Long


    override fun readTestBody() {
        dataSource = inject(DataSource::class)
        jdbcTemplate = JdbcTemplate(dataSource)

        this.body.invoke(this)
        super.readTestBody()
    }
}
