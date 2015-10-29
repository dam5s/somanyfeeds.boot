package com.somanyfeeds.jsonserialization

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.time.LocalDateTime

class ObjectMapperProvider {
    fun get(): ObjectMapper {
        val module = SimpleModule().apply {
            addSerializer(LocalDateTime::class.java, object : JsonSerializer<LocalDateTime>() {
                override fun serialize(value: LocalDateTime, jgen: JsonGenerator, provider: SerializerProvider) {
                    jgen.writeString("${value}Z")
                }
            })
        }

        return ObjectMapper().apply {
            registerKotlinModule()
            registerModule(module)
        }
    }
}
