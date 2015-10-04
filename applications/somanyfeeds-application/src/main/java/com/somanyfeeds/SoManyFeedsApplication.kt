package com.somanyfeeds

import com.somanyfeeds.jsonserialization.ObjectMapperProvider
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import java.util.*

@SpringBootApplication
open class SoManyFeedsApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
            SpringApplication.run(SoManyFeedsApplication::class.java, *args)
        }
    }

    @Bean
    @Primary
    open fun objectMapper() = ObjectMapperProvider().get()
}
