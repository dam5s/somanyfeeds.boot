package com.somanyfeeds.articledataaccess;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface ArticleCrudRepository extends CrudRepository<ArticleCrudEntity, Long> {

    @Query(value = "select a.* from article a " +
        "inner join feed f on f.id = a.feed_id " +
        "and f.slug IN (?1)", nativeQuery = true) List<ArticleCrudEntity> findAllBySlugs(List<String> slugs);

    @Modifying
    @Query(value = "delete from article where feed_id = ?1", nativeQuery = true)
    void deleteByFeedId(Long feedId);
}
