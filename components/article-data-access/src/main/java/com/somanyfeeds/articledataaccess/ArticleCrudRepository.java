package com.somanyfeeds.articledataaccess;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ArticleCrudRepository extends CrudRepository<ArticleCrudEntity, Long> {

    @Modifying
    @Query(value = "delete from article where feed_id = ?1", nativeQuery = true)
    void deleteByFeedId(Long feedId);
}
