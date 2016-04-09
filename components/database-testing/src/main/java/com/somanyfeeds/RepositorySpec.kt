package com.somanyfeeds

import io.damo.kspec.Spec
import io.damo.kspec.spring.SpringTransactionalSpecTreeRunner
import io.damo.kspec.spring.inject
import org.junit.runner.RunWith
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener
import org.springframework.test.context.transaction.TransactionalTestExecutionListener
import java.util.*


@RunWith(SpringTransactionalSpecTreeRunner::class)
@SpringApplicationConfiguration(classes = arrayOf(RepositoryTestConfiguration::class))
@TestExecutionListeners(TransactionalTestExecutionListener::class, SqlScriptsTestExecutionListener::class)
open class RepositorySpec : Spec {

    lateinit var jdbcTemplate: JdbcTemplate


    private val body: RepositorySpec.() -> Unit

    constructor(body: RepositorySpec.() -> Unit) : super({}) {
        this.body = body
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
    }


    fun execSql(sql: String) = jdbcTemplate.execute(sql)

    @Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")
    fun getCount(sql: String) = jdbcTemplate.queryForObject(sql, java.lang.Long::class.java) as Long


    override fun readSpecBody() {
        jdbcTemplate = inject(JdbcTemplate::class)

        this.body.invoke(this)
        super.readSpecBody()
    }
}
