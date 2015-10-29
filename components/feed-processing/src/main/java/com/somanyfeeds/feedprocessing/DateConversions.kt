package com.somanyfeeds.feedprocessing

import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

internal fun Date.toLocalDateTime() = LocalDateTime.ofInstant(toInstant(), ZoneId.of("UTC"))
