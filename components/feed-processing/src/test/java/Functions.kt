import java.io.InputStream
import java.util.*

public fun getResourceAsStream(path: String): InputStream {
    val classLoader = Thread.currentThread().contextClassLoader
    return classLoader.getResourceAsStream(path)
}

public fun InputStream.asString(): String {
    val s = Scanner(this).useDelimiter("\\A");
    return if (s.hasNext()) s.next() else "";
}
