package com.somanyfeeds.articledataaccess

import java.sql.ResultSet
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

fun LocalDateTime.toDate() = Date.from(atZone(ZoneId.systemDefault()).toInstant())

fun ResultSet.getLocalDateTime(column: Int) = LocalDateTime.parse(getString(column).replace(" ", "T"))
