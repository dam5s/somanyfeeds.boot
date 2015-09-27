package com.somanyfeeds.dbtesting

import org.hibernate.jpa.HibernatePersistenceProvider
import org.postgresql.ds.PGPoolingDataSource
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import java.util.*


open class RepositoryTest {

    val dataSource = PGPoolingDataSource().apply {
        databaseName = "somanyfeeds_test"
        user = "dam5s"
        serverName = "localhost"
    }
    val jdbcTemplate = JdbcTemplate(dataSource)
    val namedParameterJdbcTemplate = NamedParameterJdbcTemplate(dataSource)
    val jpaRepoFactory: JpaRepositoryFactory


    constructor() {
        val entityManagerFactoryBean = LocalContainerEntityManagerFactoryBean().apply {
            setDataSource(dataSource)
            setPackagesToScan("com.somanyfeeds")
            setJpaVendorAdapter(HibernateJpaVendorAdapter())
            setJpaProperties(Properties().apply {
                put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
                put("hibernate.connection.driver_class", "org.postgresql.Driver");
            })
            setPersistenceUnitName("test-db")
            setPersistenceProviderClass(HibernatePersistenceProvider::class.java)
            afterPropertiesSet()
        }

        val entityManagerFactory = entityManagerFactoryBean.getObject()
        val entityManager = entityManagerFactory.createEntityManager()

        jpaRepoFactory = JpaRepositoryFactory(entityManager)
    }

    fun execSql(sql: String) {
        jdbcTemplate.execute(sql)
    }
}
