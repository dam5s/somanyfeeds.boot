package testing

import com.somanyfeeds.articledataaccess.ArticleEntity
import com.somanyfeeds.articledataaccess.ArticlesRepository
import java.util.*

class FakeArticlesRepository(var articles: MutableList<ArticleEntity> = arrayListOf()) : ArticlesRepository {

    override fun exists(id: Long?): Boolean = articles.any { it.id == id }

    override fun delete(entity: ArticleEntity) = delete(entity.id)

    override fun delete(id: Long?) {
        val matches = articles.filter { it.id == id }

        if (matches.size() > 0) {
            articles.remove(matches.first())
        }
    }

    override fun delete(entities: MutableIterable<ArticleEntity>) = entities.forEach { delete(it.id) }

    @Suppress("UNCHECKED_CAST")
    override fun <S : ArticleEntity> save(entity: S): S {
        articles.add(entity)
        return entity.copy(id = Random().nextLong()) as S
    }

    override fun <S : ArticleEntity> save(entities: MutableIterable<S>): MutableIterable<S>?
        = entities.map { save(it) }.toArrayList()

    override fun findOne(id: Long): ArticleEntity? = articles.first { it.id == id }

    override fun findAll(ids: MutableIterable<Long>): MutableIterable<ArticleEntity>?
        = articles.filter { ids.contains(it.id) }.toArrayList()

    override fun findAll(): MutableIterable<ArticleEntity>? = articles

    override fun deleteAll() = articles.clear()

    override fun count(): Long = articles.size().toLong()
}
