package com.somanyfeeds.somanyfeedsapplication

import com.somanyfeeds.jsonserialization.ObjectMapperProvider
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary

@SpringBootApplication
open class SoManyFeedsApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(SoManyFeedsApplication::class.java, *args)
        }
    }

    @Bean
    @Primary
    open fun objectMapper() = ObjectMapperProvider().get()
}
