package com.somanyfeeds.articledataaccess;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ArticleCrudRepository extends CrudRepository<ArticleCrudEntity, Long> {
}
