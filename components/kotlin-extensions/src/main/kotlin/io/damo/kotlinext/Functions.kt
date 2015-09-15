package io.damo.kotlinext

public inline fun <T> T.apply(f: T.() -> Unit): T {
    this.f()
    return this
}
