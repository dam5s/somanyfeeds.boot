package io.damo.kotlinext

import kotlin.properties.Delegates
import kotlin.properties.ReadWriteProperty

public fun <T: Any> nn(): ReadWriteProperty<Any?, T> = Delegates.notNull<T>()
